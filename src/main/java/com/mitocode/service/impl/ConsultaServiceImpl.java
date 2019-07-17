package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.model.Consulta;
import com.mitocode.model.DetalleConsulta;
import com.mitocode.repo.IConsultaExamenRepo;
import com.mitocode.repo.IConsultaRepo;
import com.mitocode.service.IConsultaService;

@Service
public class ConsultaServiceImpl implements IConsultaService {

	@Autowired
	private IConsultaRepo repo;
	
	@Autowired
	private IConsultaExamenRepo repoCE;
	
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO consultaDTO) {
		consultaDTO.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(consultaDTO.getConsulta()));
		repo.save(consultaDTO.getConsulta());
		consultaDTO.getListExamen().forEach(e -> repoCE.registrar(consultaDTO.getConsulta().getIdConsulta(), e.getIdExamen() ));
		
		return consultaDTO.getConsulta();
	}

	@Override
	public Consulta registrar(Consulta cons) {
		//cons.getDetalleConsulta().forEach(det -> det.setConsulta(cons));
		for(DetalleConsulta det : cons.getDetalleConsulta()) {
			det.setConsulta(cons);
		}
		return repo.save(cons);
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
