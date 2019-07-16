package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Consulta;
import com.mitocode.repo.IConsultaRepo;
import com.mitocode.service.IConsultaService;

@Service
public class ConsultaServiceImpl implements IConsultaService {

	@Autowired
	private IConsultaRepo repo;

	@Override
	public Consulta registrar(Consulta e) {
		return repo.save(e);
	}

	@Override
	public Consulta modificar(Consulta e) {
		return repo.save(e);
	}

	@Override
	public Consulta leer(Integer idConsulta) {
		return repo.findOne(idConsulta);
	}

	@Override
	public List<Consulta> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer idConsulta) {
		repo.delete(idConsulta);
	}

}
