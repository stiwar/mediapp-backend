package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Especialidad;
import com.mitocode.service.IEspecialidadService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

	@Autowired
	private IEspecialidadService service;

	@GetMapping
	public List<Especialidad> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Especialidad listarPorId(@PathVariable("id") Integer idEspecialidad) {
		return service.leer(idEspecialidad);
	}

	@PostMapping
	public Especialidad registrar(@RequestBody Especialidad Especialidad) {
		return service.registrar(Especialidad);
	}

	@PutMapping
	public Especialidad modificar(Especialidad Especialidad) {
		return service.modificar(Especialidad);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer idEspecialidad) {
		service.eliminar(idEspecialidad);
	}
}
