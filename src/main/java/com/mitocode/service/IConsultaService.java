package com.mitocode.service;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta>{
	
	Consulta registrarTransaccional(ConsultaListaExamenDTO consultaDTO);
	
}
