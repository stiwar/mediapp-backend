package com.mitocode.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.SignoPacienteDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Signos;
import com.mitocode.service.ISignoService;

@RestController
@RequestMapping("/signos")
public class SignoController {

	@Autowired
	private ISignoService signoService;

	@GetMapping("/pageable")
	public ResponseEntity<Page<SignoPacienteDTO>> listarPageable(Pageable pageable) {

		Page<SignoPacienteDTO> signos = signoService.listarPageable(pageable);

		return new ResponseEntity<>(signos, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Signos> listarSignoPorId(@PathVariable("id") Integer idSigno) {
		Signos signo = signoService.leer(idSigno);
		return new ResponseEntity<>(signo, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Signos> registrar(@Valid @RequestBody Signos signo) {
		Signos signoRegistrado = signoService.registrar(signo);
		return new ResponseEntity<>(signoRegistrado, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Signos signo) {
		signoService.modificar(signo);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{idSigno}")
	public ResponseEntity<Object> eliminar(@PathVariable("idSigno") Integer idSigno) {

		Signos signo = signoService.leer(idSigno);
		if (signo == null)
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + idSigno);
		else
			signoService.eliminar(idSigno);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
