package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Examen;
import com.mitocode.repo.IExamenRepo;
import com.mitocode.service.IExamenService;

@Service
public class ExamenServiceImpl implements IExamenService {

	@Autowired
	private IExamenRepo repo;

	@Override
	public Examen registrar(Examen e) {
		return repo.save(e);
	}

	@Override
	public Examen modificar(Examen e) {
		return repo.save(e);
	}

	@Override
	public Examen leer(Integer idExamen) {
		return repo.findOne(idExamen);
	}

	@Override
	public List<Examen> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer idExamen) {
		repo.delete(idExamen);
	}

}
