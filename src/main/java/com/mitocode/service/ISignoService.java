package com.mitocode.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mitocode.dto.SignoPacienteDTO;
import com.mitocode.model.Signos;

public interface ISignoService extends ICRUD<Signos>{
	
	Page<SignoPacienteDTO> listarPageable(Pageable pageable);

}
