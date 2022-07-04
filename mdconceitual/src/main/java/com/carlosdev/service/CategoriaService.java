package com.carlosdev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.carlosdev.domain.Categoria;
import com.carlosdev.domain.Cliente;
import com.carlosdev.dto.CategoriaDTO;
import com.carlosdev.repository.CategoriaRepository;
import com.carlosdev.service.exception.DataIntegrityException;
import com.carlosdev.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository catRepo;
	
	
	public Categoria find(Integer id) {
		
		Optional<Categoria> objCat = catRepo.findById(id);
		
		return objCat.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! id: " +id + ",Tipo: "+ Categoria.class.getName()) );
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return catRepo.save(categoria);
	}

	public Categoria update(Categoria objCategoria) {
		
		 Categoria newObjCategoria = find(objCategoria.getId());
		   
		   updateData(newObjCategoria,objCategoria);
			
			
			
			// ATUALIZANDO A CATEGORIA
		   // SALVANDO NOVO OBJETO ATRAVES DA S INFORMAÇÕES DO BANCO
			return catRepo.save(newObjCategoria);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		find(id);
		
		try {
		 catRepo.deleteById(id);
		}
			catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
			}
		
	}

	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return catRepo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy,String direction){
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return catRepo.findAll(pageRequest);
	}
	
	// CONVERSAO DE OBJDTO
	public Categoria fromDTO(CategoriaDTO objDTO) {
		
		return new Categoria(objDTO.getId(), objDTO.getNome());
		
	}
	
	private void updateData(Categoria newObjCategoria, Categoria objCategoria) {
		newObjCategoria.setNome(objCategoria.getNome());
		
	}
	

}
