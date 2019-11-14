package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mitocode.model.Consulta;

public interface IConsultaRepo extends JpaRepository<Consulta, Integer>{
	
	@Query("FROM Consulta")//no es necesario el SELECT
	List<Consulta> buscar(String dni, String nombreCompleto);
	

}
