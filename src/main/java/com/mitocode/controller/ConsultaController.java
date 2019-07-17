package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Consulta;
import com.mitocode.service.IConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private IConsultaService service;

	@GetMapping
	public ResponseEntity<List<Consulta>> listar() {
		List<Consulta> consultas = service.listar();
		return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<Consulta> listarPorId(@PathVariable("id") Integer idConsulta) {
//
//		Consulta consulta = service.leer(idConsulta);
//		if (consulta == null)
//			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + idConsulta);
//
//		return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);
//	}
	
	@GetMapping("/{id}")
	public Resource<Consulta> listarPorId(@PathVariable("id") Integer idConsulta) {

		Consulta consulta = service.leer(idConsulta);
		if (consulta == null)
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + idConsulta);
		
		Resource<Consulta> resource = new Resource<Consulta>(consulta);
		//localhost:8080/consultas/{id}
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(idConsulta));//this.getClass() xq tiene la ruta /consultas y listarPorId(idConsulta) es xq tiene la ruta /{id}, con esto construyo la url: /consultas/{id} 
		resource.add(linkTo.withRel("consulta-resource"));
		return resource;
	}

//	@PostMapping
//	public ResponseEntity<Consulta> registrar(@Valid @RequestBody Consulta consulta) {
//		Consulta con = service.registrar(consulta);
//		return new ResponseEntity<Consulta>(con, HttpStatus.CREATED);
//	}
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> registrar(@Valid @RequestBody ConsultaListaExamenDTO consultaDTO) {
		Consulta pac = service.registrarTransaccional(consultaDTO);
		//localhost:8080/consultas/{id}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pac.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Object> modificar(@Valid @RequestBody Consulta consulta) {
		service.modificar(consulta);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer idConsulta) {

		Consulta consulta = service.leer(idConsulta);
		if (consulta == null)
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + idConsulta);
		else
			service.eliminar(idConsulta);
		
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

}
