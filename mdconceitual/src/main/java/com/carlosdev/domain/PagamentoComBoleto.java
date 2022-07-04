package com.carlosdev.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.carlosdev.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {
	

	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern = "dd/MM/yyyy ")
	private Date dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy ")
	private Date datapagamento;
	
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estadoPagamento, pedido);
		// TODO Auto-generated constructor stub
		
		this.dataVencimento = dataVencimento;
		this.datapagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDatapagamento() {
		return datapagamento;
	}

	public void setDatapagamento(Date datapagamento) {
		this.datapagamento = datapagamento;
	}

	
	
	

}
