package com.mitocode.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Signos;

public interface ISignoRepo extends JpaRepository<Signos, Integer> {
	
	@Query(value = "SELECT s, CONCAT(p.nombres, ' ', p.apellidos) FROM Signos s INNER JOIN s.paciente p")
	List<Object[]> listarSignosPaginados(Pageable pageable);
	
	//método no utilizado pero se deja a modo de estudio
	@Query("SELECT s, CONCAT(p.nombres, ' ', p.apellidos) FROM Signos s INNER JOIN s.paciente p WHERE s.idSigno = :idSigno")
	List<Object[]> listarSignoPacientePorId(@Param("idSigno") Integer idSigno);

}
