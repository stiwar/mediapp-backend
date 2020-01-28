package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Consulta;
import com.mitocode.model.ConsultaExamen;

public interface IConsultaExamenRepo extends JpaRepository<Consulta, Integer> {

	//@Transactional //SIEMPRE VA obligado cuando se utiliza @Modifying, pero en este caso es mejor ponerlo en la capa Service en ConsultaServiceImpl
	@Modifying //se utiliza en sentencias de tipo insert, update, delete sino se pone, genera error "La consulta no retornó ningún resultado"
	@Query(value = "INSERT INTO consulta_examen(id_consulta,id_examen) VALUES (:idConsulta, :idExamen)", nativeQuery = true)
	Integer registrar(@Param("idConsulta") Integer idConsulta, @Param("idExamen") Integer idExamen);
	
	@Query("FROM ConsultaExamen ce WHERE ce.consulta.idConsulta = :idConsulta")
	List<ConsultaExamen> listarExamenesPorConsulta(@Param("idConsulta") Integer idconsulta);
}
