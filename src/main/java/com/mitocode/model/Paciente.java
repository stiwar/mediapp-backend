package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Información del paciente") //para la documentación de swagger
@Entity
@Table(name = "paciente")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPaciente;

	@ApiModelProperty(notes = "Nombres debe tener mínimo 3 caracteres") //para la documentación de swagger
	@Size(min = 3, message = "Nombres debe tener mínimo 3 caracteres")
	@Column(name = "nombres", nullable = false, length = 70)
	private String nombres;

	@ApiModelProperty(notes = "Apellidos debe tener mínimo 3 caracteres") //para la documentación de swagger
	@Size(min = 3, message = "Apellidos debe tener mínimo 3 caracteres")
	@Column(name = "apellidos", nullable = false, length = 70)
	private String apellidos;

	@ApiModelProperty(notes = "DNI debe tener mínimo 8 caracteres") //para la documentación de swagger
	@Size(min = 8, max = 10, message = "DNI debe tener mínimo 8 caracteres")
	@Column(name = "dni", nullable = false, length = 15)
	private String dni;

	@ApiModelProperty(notes = "Dirección debe tener mínimo 3 caracteres") //para la documentación de swagger
	@Size(min = 3, max = 150, message = "Dirección debe tener mínimo 3 caracteres")
	@Column(name = "direccion", nullable = true, length = 150)
	private String direccion;

	@ApiModelProperty(notes = "Teléfono debe tener 10 caracteres") //para la documentación de swagger
	@Size(min = 10, max = 10, message = "Teléfono debe tener 10 caracteres")
	@Column(name = "telefono", nullable = true, length = 10)
	private String telefono;
	
	@ApiModelProperty(notes = "Email válido") //para la documentación de swagger
	@Email
	@Column(name = "email", nullable = true, length = 55)
	private String email;

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
