package com.carlosdev.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.carlosdev.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal=Calendar.getInstance();
		
		// PEGANDO A DATA DO PEDIDO E ADICIONANDO 7 DIAS
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		
		// VOU LA E SETO A DATA COM OS SETE DIAS NO PAGAMENTO
		pagto.setDatapagamento(cal.getTime());
	}
}
