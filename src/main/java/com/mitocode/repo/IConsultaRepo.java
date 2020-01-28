package com.mitocode.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Consulta;

public interface IConsultaRepo extends JpaRepository<Consulta, Integer>{
	
	//desde el front, se envía en minúsculas y en el back en memoria con LOWER() convertimos lo que esté en BD a minúsculas, sino no hay match
	@Query("FROM Consulta c WHERE c.paciente.dni = :dni OR LOWER(c.paciente.nombres) LIKE %:nombreCompleto% OR LOWER(c.paciente.apellidos) LIKE %:nombreCompleto%")//no es necesario el SELECT en JPQL
	List<Consulta> buscar(@Param("dni") String dni, @Param("nombreCompleto") String nombreCompleto);
	
	@Query("FROM Consulta c WHERE c.fecha BETWEEN :fechaConsulta AND :fechaSgte")
	List<Consulta> buscarFecha(@Param("fechaConsulta") LocalDateTime fechaConsulta, @Param("fechaSgte") LocalDateTime fechaSgte); 

	
	//La siguiente consulta retorna (por eso el 'Object[]'):
	
	//cantidad      fecha
	//[4         ,    11/05/2019]
	//[1         ,    18/05/2019]
	//@Query(value = "SELECT * FROM fn_listarResumen()", nativeQuery = true)//para postgreSql
	@Query(value = "CALL listarResumen()", nativeQuery = true)//para mySQL
	List<Object[]> listarResumen();
}
