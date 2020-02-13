package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{
	//lo siguiente es lo mismo que si lo hubiera hecho de forma manual: @Query("FROM Usuario U WHERE U.username = :username")
	Usuario findOneByUsername(String username);
}
