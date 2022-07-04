package com.carlosdev.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.carlosdev.domain.Cidade;
import com.carlosdev.domain.Cliente;
import com.carlosdev.domain.Endereco;
import com.carlosdev.domain.enums.TipoCliente;
import com.carlosdev.dto.ClienteDTO;
import com.carlosdev.dto.ClienteNewDTO;
import com.carlosdev.repository.ClienteRepository;
import com.carlosdev.repository.EnderecoRepository;
import com.carlosdev.service.exception.DataIntegrityException;
import com.carlosdev.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository cliRepo;
	
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> objcli = cliRepo.findById(id);
		
		return objcli.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! id: " +id + ",Tipo: "+ Cliente.class.getName()) );
	}

	

	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		
		obj = cliRepo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente objCliente) {
		
		// TESTANDO SE O ID EXISTE
		//find(objCliente.getId());
		
		//instanciando atraves do banco de dados para que quando cliente seja salvo n retorne valores null
		
	   Cliente newObjCliente = find(objCliente.getId());
	   
	   updateData(newObjCliente,objCliente);
		
		
		
		// ATUALIZANDO A CATEGORIA
	   // SALVANDO NOVO OBJETO ATRAVES DA S INFORMAÇÕES DO BANCO
		return cliRepo.save(newObjCliente);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		find(id);
		
		try {
		 cliRepo.deleteById(id);
		}
			catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possivel excluir por que a entidades relacionadas");
			}
		
	}

	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return cliRepo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy,String direction){
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return cliRepo.findAll(pageRequest);
	}
	
	// CONVERSAO DE OBJDTO
	public Cliente fromDTO(ClienteDTO objDTO) {
		
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(),null,null);
		
	}
	
	// PASSANDO TODAS INFORMAÇÕES DO DTO PARA SALVAR VARIAS INFORMAÇÕES
public Cliente fromDTO(ClienteNewDTO objDTO) {
		
	
	Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipoCliente()));
	Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
	Endereco end = new Endereco(null,objDTO.getLogradouro() , objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
	cli.getEnderecos().add(end);
	cli.getTelefones().add(objDTO.getTelefone1());
	
	// ADICIONANDO MAIS DE UM TELEFONE CASO SEJA DIFERENTE DE NULO
	if( objDTO.getTelefone2() != null) {
		cli.getTelefones().add(objDTO.getTelefone2());
	}
	
	if( objDTO.getTelefone3() != null) {
		cli.getTelefones().add(objDTO.getTelefone2());
	}
	
	return cli;
	}
	
	private void updateData(Cliente newObjCliente, Cliente objCliente) {
		newObjCliente.setNome(objCliente.getNome());
		newObjCliente.setEmail(objCliente.getEmail());
	}
	


	
	
	

}
