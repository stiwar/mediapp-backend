package com.mitocode;
//esta clase contiene la configuración necesaria para habilitar Spring Security

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableWebSecurity		//para habilitar las peticiones http en spring security
@EnableGlobalMethodSecurity(prePostEnabled = true)		//para habilitar spring security de forma global, no en algunos servicios. prePostEnabled = true : para verificar que los servicios rest estén protegidos previamente ante cualquier solicitud.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${security.signing-key}") //@Value permite leer las variables declaradas en application.properties
	private String signingKey;
	
	@Value("${security.encoding-strength}")
	private Integer encodingStrength;
	
	@Value("${security.security-realm}")
	private String securityRealm;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	/* 
	 * Este atributo dataSource es el definido en el application.properties
	 * spring.datasource.driver-class-name=com.mysql.jdbc.Driver
		spring.datasource.url = jdbc:mysql://localhost:3306/mediapp?useSSL=false&allowPublicKeyRetrieval=true
		spring.datasource.username = root
		spring.datasource.password = ***
	 * */
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	//este método define el mecanismo de encriptación de claves
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean   //anotación de Spring Framework, permite reservar una instancia de una clase en el Core Container de Spring, para que Spring pueda gestionar una instancia de AuthenticationManager. Para que después pueda inyectar(autowired) la implementación de AuthenticationManager en otra clase.
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//inyección de dependencia por método
	//hubiera sido lo mismo si hubiera hecho esto:
	//	@Autowired
	//	private AuthenticationManagerBuilder auth;
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);  //se utiliza para obtener los datos del usuario.
	}
	
	//el objetivo del siguiente método es deshabilitar las configuraciones tradicionales, xq éstas serán programadas por nosotros.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http		
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //xq el back-end es independiente del front-end(Angular)
        .and()
        .httpBasic()
        .realmName(securityRealm) //este parámetro es opcional, es un apodo.
        .and()
        .csrf()  //se deshabilitan los token csrf. Xq estamos en un enfoque fullstack (no tradicional).
        .disable();        
	}
	
	//método para firmar los tokens
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signingKey);		
		return converter;
	}
	
	//para indicarle en dónde se van a guardar los tokens
	@Bean
	public TokenStore tokenStore() {
		//return new JwtTokenStore(accessTokenConverter()); //para guardar solo en memoria
		return new JdbcTokenStore(this.dataSource);     //para guardar en base de datos
	}
	
	@Bean
	@Primary   //significa que este método va a tener la importancia (preferencia) de creación frente a los métodos anteriores. Es decir, primero se va a ejecutar este método antes que los anteriores.
	public DefaultTokenServices tokenServices() {//es usado en la variable tokenServices de la clase UserController.java
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());//definimos el mecanismo de almacenamiento de los tokens (estamos llamando al método definitdo anteriormente)
		defaultTokenServices.setSupportRefreshToken(true);	//habilita para poder refrescar los tokens, es decir generar otros para reutilizar
		defaultTokenServices.setReuseRefreshToken(false);	//no permite reutilizar los tokens ya refrescados, para obligar al usuario a volver a iniciar sesión cuando su tiempo ya ha expirado
		return defaultTokenServices;
	}
}
