package com.mitocode.controller;

import javax.annotation.Resource;

import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UserController {
	
	@Resource(name = "tokenServices")
	private ConsumerTokenServices tokenServices;
	
	//servicio rest utilizado para cerrar sesión
	@GetMapping(value = "/anular/{tokenId:.*}") // el :.* es para que tome todo lo que venga después de "/anular/" lo tome como una cadena de texto
	public void revokeToken(@PathVariable("tokenId") String token) {
		tokenServices.revokeToken(token);
	}

}
