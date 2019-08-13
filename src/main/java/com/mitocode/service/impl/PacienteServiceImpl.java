package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.Paciente;
import com.mitocode.repo.IPacienteRepo;
import com.mitocode.service.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService {

	@Autowired
	private IPacienteRepo repo;

	@Override
	public Paciente registrar(Paciente p) {
		return repo.save(p);
	}

	@Override
	public Paciente modificar(Paciente p) {
		return repo.save(p);
	}

	@Override
	public Paciente leer(Integer idPaciente) {
		return repo.findOne(idPaciente);
	}

	@Override
	public List<Paciente> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer idPaciente) {
		repo.delete(idPaciente);
	}

	@Override
	public Page<Paciente> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}

}
