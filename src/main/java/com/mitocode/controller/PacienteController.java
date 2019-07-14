package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private IPacienteService service;

	@GetMapping
	public ResponseEntity<List<Paciente>> listar() {
		List<Paciente> pacientes = service.listar();
		return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paciente> listarPorId(@PathVariable("id") Integer idPaciente) {

		Paciente paciente = service.leer(idPaciente);
		if (paciente == null)
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + idPaciente);

		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}

//	@PostMapping
//	public ResponseEntity<Paciente> registrar(@Valid @RequestBody Paciente paciente) {
//		Paciente pac = service.modificar(paciente);
//		return new ResponseEntity<Paciente>(pac, HttpStatus.CREATED);
//	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Paciente paciente) {
		Paciente pac = service.modificar(paciente);
		//localhost:8080/pacientes/{id}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pac.getIdPaciente()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Object> modificar(@Valid @RequestBody Paciente paciente) {
		service.modificar(paciente);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer idPaciente) {

		Paciente paciente = service.leer(idPaciente);
		if (paciente == null)
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + idPaciente);
		else
			service.eliminar(idPaciente);
		
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

}
