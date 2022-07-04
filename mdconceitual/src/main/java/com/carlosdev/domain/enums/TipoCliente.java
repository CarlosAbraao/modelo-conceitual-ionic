package com.carlosdev.domain.enums;

public enum TipoCliente {
	
	
	PESSOAFISICA(1,"Pessoa Física"),
	PESSOJURIDICA(2, "Pessoa Jurídica");
	
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
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
	public static TipoCliente toEnum(Integer cod){
		
		if(cod == null) {
			return null;
		}
		
		// VERIFICA SE O CODIGO DO CLIENTE PASSADO NO PARAMETRO É O MESMO  PERCORRIDO
		for(TipoCliente x: TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		// EXCEPTION LANÇADA CASO NÃO ENCONTRE O VALOR
		throw new IllegalArgumentException("Id: invalido" + cod);
	
	
	
	}
}
