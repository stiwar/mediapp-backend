package com.mitocode.dto;

import com.mitocode.model.Signos;

public class SignoPacienteDTO {

	private Signos signo;
	private String nombrePacienteCompleto;

	public SignoPacienteDTO(Signos signo, String nombrePacienteCompleto) {
		this.signo = signo;
		this.nombrePacienteCompleto = nombrePacienteCompleto;
	}

	public Signos getSigno() {
		return signo;
	}

	public void setSigno(Signos signo) {
		this.signo = signo;
	}

	public String getNombrePacienteCompleto() {
		return nombrePacienteCompleto;
	}

	public void setNombrePacienteCompleto(String nombrePacienteCompleto) {
		this.nombrePacienteCompleto = nombrePacienteCompleto;
	}

}
