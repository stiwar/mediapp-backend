package com.mitocode.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.ResetToken;
import com.mitocode.model.Usuario;
import com.mitocode.service.ILoginService;
import com.mitocode.service.IResetTokenService;
import com.mitocode.util.EmailService;
import com.mitocode.util.Mail;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private ILoginService service;
	
	@Autowired
	private IResetTokenService tokenService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@PostMapping(value = "/enviarCorreo", consumes = MediaType.TEXT_PLAIN_VALUE) //TEXT_PLAIN_VALUE porque desde el front me llega un String no un objecto tipo comando
	public ResponseEntity<Integer> enviarCorreo( @RequestBody String correo ){
		
		int rpta = 0;
		
		try {
			
			Usuario us = service.verificarNombreUsuario(correo);
			
			if(us != null && us.getIdUsuario() > 0) {//una vez el usuario esté validado, es factible crear la URL con el token de reseteo (parte aleatoria de la URL)
				
				//Generar y Guardar Token en BD
				ResetToken token = new ResetToken(); //parte aleatoria
				token.setToken(UUID.randomUUID().toString()); //UUID.randomUUID() genera una cadena aleatoria
				token.setUsuario(us);
				token.setExpiracion(10); //10 minutos
				tokenService.guardar(token);
				
				//Enviar Mail
				Mail mail = new Mail();
				mail.setFrom("stivenartesol@gmail.com");
				mail.setTo(us.getUsername());
				mail.setSubject("RESTABLECER CONTRASEÑA - MEDIAPP");
				
				Map<String, Object> model = new HashMap<>();
				String url = "http://localhost:4200/recuperar/" + token.getToken();
				model.put("user", token.getUsuario().getUsername());
				model.put("resetUrl", url);
				mail.setModel(model);
				emailService.sendEmail(mail);
				
				rpta = 1;
				
			}
			
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Integer>(rpta,HttpStatus.OK);
	}

}
