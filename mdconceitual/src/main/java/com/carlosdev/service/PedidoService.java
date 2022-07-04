package com.carlosdev.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosdev.domain.ItemPedido;
import com.carlosdev.domain.PagamentoComBoleto;
import com.carlosdev.domain.Pedido;
import com.carlosdev.domain.Produto;
import com.carlosdev.domain.enums.EstadoPagamento;
import com.carlosdev.repository.ItemPedidoRepository;
import com.carlosdev.repository.PagamentoRepository;
import com.carlosdev.repository.PedidoRepository;
import com.carlosdev.repository.ProdutoRepository;
import com.carlosdev.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	public Pedido busca(Integer id) {
		
		Optional<Pedido> objCat = pedidoRepo.findById(id);
		
		return objCat.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! id: " +id + ",Tipo: "+ Pedido.class.getName()) );
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto ) {
			 PagamentoComBoleto pagto =(PagamentoComBoleto) obj.getPagamento();
			 boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
			obj = pedidoRepo.save(obj);
			 pagamentoRepository.save(obj.getPagamento());
			 
			
			 for (ItemPedido ip : obj.getItens()) {
					ip.setDesconto(0.0);
					ip.setProduto(produtoService.find(ip.getProduto().getId()));
					ip.setPreco(ip.getProduto().getPreco());
					ip.setPedido(obj);
				}
			 itemPedidoRepository.saveAll(obj.getItens());
			 return obj;

			 }
		
		
	}


