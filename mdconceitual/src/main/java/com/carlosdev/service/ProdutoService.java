package com.carlosdev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.carlosdev.domain.Categoria;
import com.carlosdev.domain.Produto;
import com.carlosdev.repository.CategoriaRepository;
import com.carlosdev.repository.ProdutoRepository;
import com.carlosdev.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository pedidoRepo;
	
	
	
	@Autowired
	private CategoriaRepository categoriaRepository ;
	
	@Autowired
	private ProdutoRepository produtoRepository ;
	
	
	public Produto busca(Integer id) {
		
		Optional<Produto> objCat = pedidoRepo.findById(id);
		
		return objCat.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! id: " +id + ",Tipo: "+ Produto.class.getName()) );
	}
	
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return pedidoRepo.search(nome, categorias, pageRequest);
	}
	
	
public Produto find(Integer id) {
		
		Optional<Produto> objProd = produtoRepository.findById(id);
		
		return objProd.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! id: " +id + ",Tipo: "+ Produto.class.getName()) );
	}

}
