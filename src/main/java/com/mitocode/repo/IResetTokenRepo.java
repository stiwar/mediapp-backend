package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.ResetToken;

public interface IResetTokenRepo extends JpaRepository<ResetToken, Long>{
	
	//FROM ResetToken WHERE token = :? (es lo mismo que hace findByToken, leer documentación de Spring Data JPA)
	ResetToken findByToken(String token);

}
