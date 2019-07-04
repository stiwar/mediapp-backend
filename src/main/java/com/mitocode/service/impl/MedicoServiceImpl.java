package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Medico;
import com.mitocode.repo.IMedicoRepo;
import com.mitocode.service.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService {

	@Autowired
	private IMedicoRepo repo;

	@Override
	public Medico registrar(Medico m) {
		return repo.save(m);
	}

	@Override
	public Medico modificar(Medico m) {
		return repo.save(m);
	}

	@Override
	public Medico leer(Integer idMedico) {
		return repo.findOne(idMedico);
	}

	@Override
	public List<Medico> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer idMedico) {
		repo.delete(idMedico);
	}

}
