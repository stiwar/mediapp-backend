package com.mitocode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.dto.SignoPacienteDTO;
import com.mitocode.model.Signos;
import com.mitocode.repo.ISignoRepo;
import com.mitocode.service.ISignoService;

@Service
public class SignoServiceImpl implements ISignoService{
	
	private static final int SEGUNDA_POSICION_EN_LISTA_O_ARRAY = 1;
	private static final int PRIMER_POSICION_EN_LISTA_O_ARRAY = 0;
	
	@Autowired
	private ISignoRepo signoRepo;

	@Override
	public Page<SignoPacienteDTO> listarPageable(Pageable pageable) {
		List<SignoPacienteDTO> listaSignosDTO = convertirListaObjectosSignosAPageDeSignoPacienteDto(pageable); 
		return new PageImpl<>(listaSignosDTO, pageable, signoRepo.count());

	}

	@Override
	public Signos registrar(Signos signo) {
		return signoRepo.save(signo);
	}

	@Override
	public Signos modificar(Signos signo) {
		return signoRepo.save(signo);
	}

	@Override
	public Signos leer(Integer idSigno) {
		//return convertirListaObjectosSignosAObjetoDeSignoPacienteDto(idSigno);
		return signoRepo.findOne(idSigno);
	}

	@Override
	public List<Signos> listar() {
		return null;
	}

	@Override
	public void eliminar(Integer idSigno) {
		signoRepo.delete(idSigno);
	}
	
	private List<SignoPacienteDTO> convertirListaObjectosSignosAPageDeSignoPacienteDto(Pageable pageable) {
		
		List<SignoPacienteDTO> listaSignosDTO = new ArrayList<>(); 
		
		signoRepo.listarSignosPaginados(pageable).forEach(
				
			fila -> {
				SignoPacienteDTO signoPacienteDTO = new SignoPacienteDTO( (Signos) fila[0], String.valueOf(fila[1]) );
				listaSignosDTO.add(signoPacienteDTO);
			}
		);
		
		return listaSignosDTO;
	}
	
	private SignoPacienteDTO convertirListaObjectosSignosAObjetoDeSignoPacienteDto(Integer idSigno) {
		
		List<SignoPacienteDTO> listaSignosDTO = new ArrayList<>(); 
		
		signoRepo.listarSignoPacientePorId(idSigno).forEach(
				
			fila -> {
				SignoPacienteDTO signoPacienteDTO = new SignoPacienteDTO( (Signos) fila[PRIMER_POSICION_EN_LISTA_O_ARRAY], String.valueOf(fila[SEGUNDA_POSICION_EN_LISTA_O_ARRAY]) );
				listaSignosDTO.add(signoPacienteDTO);
			}
		);
		
		return listaSignosDTO.get(PRIMER_POSICION_EN_LISTA_O_ARRAY);
	}

}
