	package com.carlosdev.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.carlosdev.service.validation.ClienteInsert;


@ClienteInsert
public class ClienteNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "O campo não pode ser vazio")
	@Length(min = 5, max = 80, message = "O campo nome de ve ser menor do que 80 caracteres")
	private String nome;
	
	@NotEmpty(message = "O campo não pode ser vazio")
	@Email(message = "Email invalido")
	private String email;
	
	@NotEmpty(message = "O campo não pode ser vazio")
	private String cpfOuCnpj;
	// TIPO CLIENTE VAI RECEBER UM NUMERO INTEIRO
	private Integer tipoCliente;
	
	
	@NotEmpty(message = "O campo não pode ser vazio")
	private String logradouro;
	
	@NotEmpty(message = "O campo não pode ser vazio")
	private String numero;
	private String complemento;
	private String bairro;
	
	@NotEmpty(message = "O campo não pode ser vazio")
	private String cep;
	
	
	@NotEmpty(message = "O campo não pode ser vazio")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;
	
	public ClienteNewDTO() {
		
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	

}
