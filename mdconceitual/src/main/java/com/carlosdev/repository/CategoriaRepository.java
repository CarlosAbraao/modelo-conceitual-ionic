package com.carlosdev.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlosdev.domain.Categoria;
import com.carlosdev.domain.Produto;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	

}
