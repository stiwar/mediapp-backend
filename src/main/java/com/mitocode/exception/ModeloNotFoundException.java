package com.mitocode.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)  : ya no es necesario, ver método manejarModeloExcepciones de la clase ResponseExceptionHandler
public class ModeloNotFoundException extends RuntimeException{

	public ModeloNotFoundException(String mensaje) {
		super(mensaje);
	}
	
}