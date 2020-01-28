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

import com.mitocode.model.Medico;
import com.mitocode.service.IMedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private IMedicoService service;

	@GetMapping
	public List<Medico> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Medico listarPorId(@PathVariable("id") Integer idMedico) {
		return service.leer(idMedico);
	}

	@PostMapping
	public Medico registrar(@RequestBody Medico medico) {
		return service.registrar(medico);
	}

	@PutMapping
	public Medico modificar(@RequestBody Medico medico) {
		return service.modificar(medico);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer idMedico) {
		service.eliminar(idMedico);
	}
}