package com.carlosdev.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlosdev.domain.Categoria;
import com.carlosdev.domain.Cidade;
import com.carlosdev.domain.Cliente;
import com.carlosdev.domain.Endereco;
import com.carlosdev.domain.Estado;
import com.carlosdev.domain.ItemPedido;
import com.carlosdev.domain.Pagamento;
import com.carlosdev.domain.PagamentoComCartao;
import com.carlosdev.domain.Pedido;
import com.carlosdev.domain.Produto;
import com.carlosdev.domain.enums.EstadoPagamento;
import com.carlosdev.domain.enums.TipoCliente;
import com.carlosdev.repository.CategoriaRepository;
import com.carlosdev.repository.CidadeRepository;
import com.carlosdev.repository.ClienteRepository;
import com.carlosdev.repository.EnderecoRepository;
import com.carlosdev.repository.EstadoRepository;
import com.carlosdev.repository.ItemPedidoRepository;
import com.carlosdev.repository.PagamentoRepository;
import com.carlosdev.repository.PedidoRepository;
import com.carlosdev.repository.ProdutoRepository;

@Service
public class DBService {
	
	
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	
	public void instatiateTestDataBase() throws ParseException {
		
		
		

		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria (null, "Eletrodoméstico");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria (null, "Automotivo");
		Categoria cat5 = new Categoria(null, "Farmacia");
		Categoria cat6 = new Categoria (null, "Jardinagem");
		Categoria cat7 = new Categoria(null, "Escolar");
		Categoria cat8 = new Categoria (null, "Cinema");
		
		Produto prod1 = new Produto(null, "Computador", 2400.00);
		Produto prod2 = new Produto (null, "Impressora", 800.00);
		Produto prod3 = new Produto (null, "Mouse", 80.00);
		Produto prod4= new Produto(null, "Mesa de Escritório", 500.00);
		Produto prod5 = new Produto (null, "Toalha", 45.00);
		Produto prod6 = new Produto (null, "Colcha", 90.00);
		Produto prod7 = new Produto(null, "Tv Philco", 3000.00);
		Produto prod8 = new Produto (null, "Roçadeira", 387.00);
		Produto prod9 = new Produto (null, "Abajour", 19.00);
		Produto prod10 = new Produto(null, "Cama Casal", 2400.00);
		Produto prod11 = new Produto (null, "Shampoo", 15.00);
		Produto prod12 = new Produto (null, "Hd Kingstone", 350.00);
		Produto prod13 = new Produto (null, "Cadeira Gamer", 850.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod12, prod13));
		cat2.getProdutos().addAll(Arrays.asList(prod7, prod8,prod9));
		cat3.getProdutos().addAll(Arrays.asList(prod4,prod5, prod6,prod10));
		cat5.getProdutos().addAll(Arrays.asList(prod11));
		
		
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		prod12.getCategorias().addAll(Arrays.asList(cat1));
		prod13.getCategorias().addAll(Arrays.asList(cat1));
		prod7.getCategorias().addAll(Arrays.asList(cat2));
		prod8.getCategorias().addAll(Arrays.asList(cat2));
		prod9.getCategorias().addAll(Arrays.asList(cat2));
		prod4.getCategorias().addAll(Arrays.asList(cat3));
		prod5.getCategorias().addAll(Arrays.asList(cat3));
		prod6.getCategorias().addAll(Arrays.asList(cat3));
		prod10.getCategorias().addAll(Arrays.asList(cat3));
		prod11.getCategorias().addAll(Arrays.asList(cat5));
		
		
		
		
		// POPULANDO ESTADO 
		
		Estado est1 = new Estado(null,"Maranhão");
		Estado est2 = new Estado(null, "São Paulo");
		
		// POPULANDO CIDADE E RELACIONANDO 
		
		Cidade cid1 = new Cidade(null,"São Luís",est1);
		Cidade cid2 = new Cidade (null, "São Paulo",est2);
		Cidade cid3 = new Cidade (null, "Imperatriz",est1);
		
		
		// RELACIONANDO ESTADO COM CIDADE
		
		est1.getCidades().addAll(Arrays.asList(cid1, cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));
		
		
		
		//CRIANDO CLIENTE
		
		Cliente cli1 = new Cliente(null,"Carlos Abraão","carloschavesnd@gmail.com","321.546.484.58",TipoCliente.PESSOAFISICA);
		
		//ADICIONANDO O TELEFONE AO CLIENTE
		
		cli1.getTelefones().addAll(Arrays.asList("983259-4578", "989654-89652"));
		
		//CRIANDO E ADICIONANDO ENDEREÇO AO CLIENTE
		
		Endereco e1 = new Endereco(null, "Alameda Porto Molhado", "250", "Casa", "Jardim", "3254574", cli1, cid1);
		Endereco e2 = new Endereco(null, "Arnoldo Materiz", "30", "Casa", "Jardim", "0055842", cli1, cid2);
		
		// RELACIONANDO CLIENTE-ENDEREÇO
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
		
		//CRIANDO PEDIDO
		
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				
				Pedido ped1 = new Pedido(null, sdf.parse("30/05/2017 14:50"), cli1, e1);
				Pedido ped2 = new Pedido(null, sdf.parse("05/12/2019 00:50"), cli1, e2);
				
				
			Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
			// SETANDO QUE O PEDIDO ESTA PAGO
			ped1.setPagamento(pagto1);
				
			Pagamento pagto2= new PagamentoComCartao(null, EstadoPagamento.PENDENTE, ped2, 2);
			ped2.setPagamento(pagto2);
			
			// ASSOCIANDO O CLIENTE COM OS PEDIDOS
			
			cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
			
			// SALVANDO PEDIDO
			
			pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
			pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
			
			ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
			ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
			ItemPedido ip3 = new ItemPedido(ped2, prod1, 100.00, 1, 800.00);
			
			// RELACIONAMENTO ENTRE
			ped1.getItens().addAll(Arrays.asList(ip1,ip3));
			ped1.getItens().addAll(Arrays.asList(ip2));
			
			
			// RELACIONAMENTO ENTRE 
			prod1.getItens().addAll(Arrays.asList(ip1));
			prod2.getItens().addAll(Arrays.asList(ip3));
			prod3.getItens().addAll(Arrays.asList(ip2));
			
			itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
			
			
	}

}
