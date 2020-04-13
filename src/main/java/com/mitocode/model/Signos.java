package com.mitocode.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "signos")
public class Signos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSigno;

	@ManyToOne
	@JoinColumn(name = "id_paciente") //importante esta anotación, sino el INNER JOIN explícito en listarSignosPaginados falla!
	private Paciente paciente;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fecha;

	@Column(length = 10, nullable = false)
	private String temperatura;

	@Column(length = 10, nullable = false)
	private String pulso;

	@Column(name = "ritmo_respitario", length = 10, nullable = false)
	private String ritmoRespiratorio;

	public Integer getIdSigno() {
		return idSigno;
	}
	
	public void setIdSigno(Integer idSigno) {
		this.idSigno = idSigno;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getPulso() {
		return pulso;
	}

	public void setPulso(String pulso) {
		this.pulso = pulso;
	}

	public String getRitmoRespiratorio() {
		return ritmoRespiratorio;
	}

	public void setRitmoRespiratorio(String ritmoRespiratorio) {
		this.ritmoRespiratorio = ritmoRespiratorio;
	}

}
