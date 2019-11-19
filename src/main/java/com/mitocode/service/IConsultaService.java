package com.mitocode.service;

import java.util.List;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta>{
	
	Consulta registrarTransaccional(ConsultaListaExamenDTO consultaDTO);
	
	List<Consulta> buscar(FiltroConsultaDTO filtro);
	
	List<Consulta> buscarFecha(FiltroConsultaDTO filtro);
	
}
