package com.mitocode.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ConsultaExamenPK.class)
public class ConsultaExamen {
	
	@Id
	private Examen examen;
	@Id
	private Consulta consulta;
	
}
