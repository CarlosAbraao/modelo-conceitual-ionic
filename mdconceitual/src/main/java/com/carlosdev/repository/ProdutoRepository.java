package com.carlosdev.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carlosdev.domain.Categoria;
import com.carlosdev.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categoria,
			Pageable pageRequest);
	
	// O MESMO METODO ACIMA PODE SER FEITO DA FORMA ABAIXO PELO NOME DO METODO
	/*
	 * Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,
	 * List<Categoria> categoria, Pageable pageRequest);
	 */
	

}
