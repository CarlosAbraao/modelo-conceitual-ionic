package com.carlosdev.domain.enums;

public enum EstadoPagamento {
	
	
	PENDENTE(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}



	public String getDescricao() {
		return descricao;
	}

//METODO QUE VAI PASSAR UM VALOR NUMERICO PARA O ENUM
	public static EstadoPagamento toEnum(Integer cod){
		
		if(cod == null) {
			return null;
		}
		
		// VERIFICA SE O CODIGO DO CLIENTE PASSADO NO PARAMETRO É O MESMO  PERCORRIDO
		for(EstadoPagamento x: EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		// EXCEPTION LANÇADA CASO NÃO ENCONTRE O VALOR
		throw new IllegalArgumentException("Id: invalido" + cod);
	
	
	
	}
}
