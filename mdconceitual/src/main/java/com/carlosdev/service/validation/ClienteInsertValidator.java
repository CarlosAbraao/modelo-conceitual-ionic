package com.carlosdev.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.carlosdev.controller.exception.FieldMessage;
import com.carlosdev.domain.Cliente;
import com.carlosdev.domain.enums.TipoCliente;
import com.carlosdev.dto.ClienteNewDTO;
import com.carlosdev.repository.ClienteRepository;
import com.carlosdev.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	// PUXANDO O EMAIL DO BANCO DE DADOS PARA VER SE O EMAIL EXISTE	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
			list.add(new FieldMessage("cpfOuCnpj", "CPf invalido"));
		}
		
		
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ invalido"));
		}
		
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if(aux != null ) {
			list.add(new FieldMessage("email", "email ja existente"));
			
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessagem()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
