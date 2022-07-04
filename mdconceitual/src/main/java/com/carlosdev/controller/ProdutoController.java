package com.carlosdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carlosdev.controller.utils.URL;
import com.carlosdev.domain.Categoria;
import com.carlosdev.domain.Produto;
import com.carlosdev.dto.CategoriaDTO;
import com.carlosdev.dto.ProdutoDTO;
import com.carlosdev.service.ProdutoService;



@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?>  find(@PathVariable Integer id) {
		
		Produto obj = produtoService.busca(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>>  findPage(
			@RequestParam(value ="nome", defaultValue = "") String nome, 
			@RequestParam(value ="categorias", defaultValue = "") String categorias, 
			@RequestParam(value ="page", defaultValue = "0") Integer page, 
			@RequestParam(value ="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value ="orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value ="direction", defaultValue = "ASC")  String direction) {
		
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		
		
		Page<Produto> list = produtoService.search(nomeDecoded, ids, page,  linesPerPage,  orderBy, direction);
		// CRIA UM OBJETO DTO BASEADO NO CONSTRUTUTOR DO DTO 
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		
		return ResponseEntity.ok().body(listDTO);
	}

}
