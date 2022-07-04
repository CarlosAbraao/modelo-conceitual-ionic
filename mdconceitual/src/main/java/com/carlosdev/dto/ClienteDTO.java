package com.carlosdev.dto;

import java.io.Serializable;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.carlosdev.domain.Cliente;
import com.carlosdev.service.validation.ClienteUpdate;


@ClienteUpdate
public class ClienteDTO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	@NotEmpty(message = "O campo não pode ser vazio")
	@Length(min = 5, max = 80, message = "O campo nome de ve ser menor do que 80 caracteres")
	private String nome;
	

	@NotEmpty(message = "O campo não pode ser vazio")
	@Email(message = "Email invalido")
	private String email;
		
	
	
	
	
	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public ClienteDTO(Cliente obj) {
		
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		
	}




	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public String getNome() {
		return nome;
	}





	public void setNome(String nome) {
		this.nome = nome;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	

}
