package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.mitocode.dto.ConsultaDTO;
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
	
	@GetMapping(value = "/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ConsultaDTO> listarHateoas(){
		
		List<Consulta> consultas = new ArrayList<>();
		List<ConsultaDTO> consultasDTO = new ArrayList<>();
		consultas = service.listar();
		
		for(Consulta c : consultas) {
			
			ConsultaDTO d = new ConsultaDTO();
			d.setIdConsulta(c.getIdConsulta());
			d.setMedico(c.getMedico());
			d.setPaciente(c.getPaciente());
			
			//localhost:8080/consultas/1
			ControllerLinkBuilder linkTo = linkTo(methodOn(ConsultaController.class).listarPorId(c.getIdConsulta()));
			d.add(linkTo.withSelfRel());
			consultasDTO.add(d);
			
			//localhost:8080/pacientes/1
			ControllerLinkBuilder linkTo1 = linkTo(methodOn(PacienteController.class).listarPorId(c.getPaciente().getIdPaciente()));
			d.add(linkTo1.withSelfRel());
			consultasDTO.add(d);
			
			//localhost:8080/medicos/1
			ControllerLinkBuilder linkTo2 = linkTo(methodOn(MedicoController.class).listarPorId(c.getPaciente().getIdPaciente()));
			d.add(linkTo2.withSelfRel());
			consultasDTO.add(d);
		}
		
		return consultasDTO;
		
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
