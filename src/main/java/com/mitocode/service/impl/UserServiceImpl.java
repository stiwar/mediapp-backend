package com.mitocode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mitocode.model.Usuario;
import com.mitocode.repo.IUsuarioRepo;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private IUsuarioRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = repo.findOneByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe: ", username));
		}
		//GrantedAuthority esta definido en StringSecurity para la definición de roles 
		List<GrantedAuthority> roles = new ArrayList<>();
		//los roles están disponibles aquí porque en la Entidad Usuario se configuró el atributo roles como EAGER.
		user.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getNombre())); //porque SimpleGrantedAuthority implementa la interface GrantedAuthority. 
		});
		
		UserDetails userDetails = new User(user.getUsername(), user.getPassword(), roles);
		return userDetails;
	}

}
