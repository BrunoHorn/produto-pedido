package com.senior.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senior.enumerado.TipoProdutoServico;
import com.senior.exception.EntidadeNaoEncontradaException;
import com.senior.model.ItemPedido;
import com.senior.model.Pedido;
import com.senior.model.ProdutoServico;
import com.senior.repository.ItemPedidoRepository;
import com.senior.service.ItemPedidoService;
 
@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Transactional
	@Override
	public ItemPedido save(Pedido pedido,ProdutoServico produtoServico, Integer quantidade, String observacao) {
		ItemPedido itemPedido; 
		itemPedido =itemPedidoRepository.findByProdutoServicoAndPedido(produtoServico, pedido) ;		
        if (Objects.nonNull(itemPedido))  {     	
        	itemPedido.setQuantidade(quantidade);
        	itemPedido.setObservacao(observacao);
        	return itemPedido;
        }

        itemPedido = ItemPedido.builder().quantidade(quantidade)
        					   .observacao(observacao)
        					   .produtoServico(produtoServico)
        					   .pedido(pedido)
        					   .build();
		itemPedido = itemPedidoRepository.save(itemPedido);
				
		return itemPedido;		
	}
	
	@Override
	public ItemPedido findById(UUID id)  {	
		var itemPedido = itemPedidoRepository.findById(id);		
		if(itemPedido.isEmpty()) {			
			throw new EntidadeNaoEncontradaException("Não há item de pedidos cadastrados com o ID "  + id.toString());
		}
		
		return itemPedido.get();		
	}
		
	@Override
	public void excluir(UUID id) { 	
		
		ItemPedido itemPedido = findById(id);			
		itemPedidoRepository.delete(itemPedido);		
    }
	
	@Override
	public Double calcularTotal(List<ItemPedido> itemPedidoList,Double desconto) {
		Double totalProduto = 0.0;
		Double totalServico = 0.0;
		for (ItemPedido item : itemPedidoList) {
			if (item.getProdutoServico().getTipo()== TipoProdutoServico.PRODUTO) {
				totalProduto =+ calculaValorItem(item.getProdutoServico().getValor(), item.getQuantidade());
		    }else {
		    	totalServico =+ calculaValorItem(item.getProdutoServico().getValor(), item.getQuantidade());
			}		
		}	
		var total= totalServico + aplicaDesconto(totalProduto,desconto);
				
		return total;		
	}
	
	private Double calculaValorItem(Double preco,Integer quantidade) {
		
		return (preco * quantidade);
	}
	
	private Double aplicaDesconto(Double totalProduto,Double desconto) {
		
		return totalProduto-(desconto * totalProduto / 100) ;
	}
	
}
