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

import com.mitocode.model.Examen;
import com.mitocode.service.IExamenService;

@RestController
@RequestMapping("/examenes")
public class ExamenController {

	@Autowired
	private IExamenService service;

	@GetMapping
	public List<Examen> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Examen listarPorId(@PathVariable("id") Integer idExamen) {
		return service.leer(idExamen);
	}

	@PostMapping
	public Examen registrar(@RequestBody Examen Examen) {
		return service.registrar(Examen);
	}

	@PutMapping
	public Examen modificar(Examen Examen) {
		return service.modificar(Examen);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer idExamen) {
		service.eliminar(idExamen);
	}
}
