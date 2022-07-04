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

import com.carlosdev.domain.Cliente;
import com.carlosdev.dto.ClienteDTO;
import com.carlosdev.dto.ClienteNewDTO;
import com.carlosdev.service.ClienteService;



@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService cliService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?>  find(@PathVariable Integer id) {
		
		Cliente obj = cliService.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	
	
	// METODO DTO QUE SALVA VARIAS INFORMAÇÕES NO BANCO
	

	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objClienteDTO){
		Cliente objCliente = cliService.fromDTO(objClienteDTO);
		// VOU CRIAR UMA NOVA CATEGORIA
		objCliente = cliService.insert(objCliente);
		
		// CRIAR UM CAMINHO, UMA URI PARA A CATEGORIA CRIADA
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(objCliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//////
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody ClienteDTO objClienteDTO, @PathVariable Integer id){
		Cliente objCliente = cliService.fromDTO(objClienteDTO);
		objCliente.setId(id);
		objCliente = cliService.update(objCliente);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?>  delete(@PathVariable Integer id) {
		cliService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping()
	public ResponseEntity<List<ClienteDTO>>  findAll() {
		
		List<Cliente> list = cliService.findAll();
		// CRIA UM OBJETO DTO BASEADO NO CONSTRUTUTOR DO DTO 
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>>  findPage(
			@RequestParam(value ="page", defaultValue = "0") Integer page, 
			@RequestParam(value ="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value ="orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value ="direction", defaultValue = "ASC")  String direction) {
		
		Page<Cliente> list = cliService.findPage( page,  linesPerPage,  orderBy, direction);
		// CRIA UM OBJETO DTO BASEADO NO CONSTRUTUTOR DO DTO 
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		
		return ResponseEntity.ok().body(listDTO);
	}
	

}
