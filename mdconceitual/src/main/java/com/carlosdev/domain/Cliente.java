package com.carlosdev.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.carlosdev.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Cliente implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Column(unique = true)
	private String email;
	private String cpfOuCnpj;
	// TIPO CLIENTE VAI RECEBER UM NUMERO INTEIRO
	private Integer tipoCliente;
	
	// O CLIENTE CONHECE OS PEDIDOS
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
	 
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)// A OPERAÇÃO QUE AFETAR O CLIENTE VAI AFETAR O ENDEREÇO 	 	 	
	private List<Endereco> enderecos = new ArrayList<>();
	
	// VOU CRIAR UMA LISTA DE TELEFONES COM A COLEÇÃO "SET" QUE N ACEITA REPETIÇÕES
	// ISSO PODERIA SER FEITO COM UMA CLASSE INDIVIDUAL
	
	//CRIANDO UMA TABELA BASEADA EM COLEÇÕES
	@ElementCollection
	@CollectionTable(name = "TELEFONE")		//DANDO UM NOME PARA COLUNA TELEFONE
	private Set<String> telefones = new HashSet<>();
	
	public Cliente() {
		
	}

	public Cliente(Integer id, String nome,String email, String cpfOuCnpj, TipoCliente tipoCliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.setEmail(email);
		this.cpfOuCnpj = cpfOuCnpj;
		// TIPO CLIENTE VAI PASSAR O CODIGO
		this.tipoCliente = (tipoCliente==null) ? null : tipoCliente.getCod();
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipoCliente() {
		// O TIPO CLIENTE TA PEGANDO O CODIGO E PASSANDO PARA O ENUM
		return TipoCliente.toEnum(tipoCliente);
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente.getCod();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpfOuCnpj, enderecos, id, nome, telefones, tipoCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpfOuCnpj, other.cpfOuCnpj) && Objects.equals(enderecos, other.enderecos)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(telefones, other.telefones) && tipoCliente == other.tipoCliente;
	}

	

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", cpfOuCnpj=" + cpfOuCnpj
				+ ", tipoCliente=" + tipoCliente + ", enderecos=" + enderecos + ", telefones=" + telefones + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	   
	

}
