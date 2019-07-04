package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Especialidad;
import com.mitocode.repo.IEspecialidadRepo;
import com.mitocode.service.IEspecialidadService;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

	@Autowired
	private IEspecialidadRepo repo;

	@Override
	public Especialidad registrar(Especialidad e) {
		return repo.save(e);
	}

	@Override
	public Especialidad modificar(Especialidad e) {
		return repo.save(e);
	}

	@Override
	public Especialidad leer(Integer idEspecialidad) {
		return repo.findOne(idEspecialidad);
	}

	@Override
	public List<Especialidad> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer idEspecialidad) {
		repo.delete(idEspecialidad);
	}

}
