package com.mitocode.service.impl;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RestAuthService {
	
	public boolean hasAccess(String path) {
		
		boolean rpta = false;
		String metodoRol = "";
		String metodoRoles[];
		Authentication authentication;

		// listar
		switch (path) {
		
			case "listar":
				metodoRol = "ADMIN";
				break;
	
			case "listarId":
				metodoRol = "ADMIN,USER,DBA";
				break;

		}

		metodoRoles = metodoRol.split(",");

		authentication = SecurityContextHolder.getContext().getAuthentication();//obtiene la información de quién ha iniciado sesión
		//authentication está amarrado internamente al UserDetailsService, por eso puede saber cuál es el usuario que está logueado
		if( !(authentication instanceof AnonymousAuthenticationToken) ) { //pregunto sino es un usuario anónimo, es decir, si es un usuario logueado
			
			System.out.println(authentication.getName()); //ejemplo demostrativo para obtener el nombre del ususario logueado

			for (GrantedAuthority auth : authentication.getAuthorities()) {
				String rolUser = auth.getAuthority();
				System.out.println(rolUser);

				for (String rolMet : metodoRoles) {
					if (rolUser.equalsIgnoreCase(rolMet)) {
						rpta = true;
					}
				}
			}
			
		}
		return rpta;
	}
}
