package com.carlosdev.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carlosdev.domain.Categoria;
import com.carlosdev.dto.CategoriaDTO;
import com.carlosdev.service.CategoriaService;



@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService catService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?>  find(@PathVariable Integer id) {
		
		Categoria obj = catService.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objCategoriaDTO){
		Categoria objCategoria = catService.fromDTO(objCategoriaDTO);
		// VOU CRIAR UMA NOVA CATEGORIA
		objCategoria = catService.insert(objCategoria);
		
		// CRIAR UM CAMINHO, UMA URI PARA A CATEGORIA CRIADA
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(objCategoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody CategoriaDTO objCategoriaDTO, @PathVariable Integer id){
		Categoria objCategoria = catService.fromDTO(objCategoriaDTO);
		objCategoria.setId(id);
		objCategoria = catService.update(objCategoria);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?>  delete(@PathVariable Integer id) {
		catService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>>  findAll() {
		
		List<Categoria> list = catService.findAll();
		// CRIA UM OBJETO DTO BASEADO NO CONSTRUTUTOR DO DTO 
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>>  findPage(
			@RequestParam(value ="page", defaultValue = "0") Integer page, 
			@RequestParam(value ="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value ="orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value ="direction", defaultValue = "ASC")  String direction) {
		
		Page<Categoria> list = catService.findPage( page,  linesPerPage,  orderBy, direction);
		// CRIA UM OBJETO DTO BASEADO NO CONSTRUTUTOR DO DTO 
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
		
		return ResponseEntity.ok().body(listDTO);
	}
	

}
