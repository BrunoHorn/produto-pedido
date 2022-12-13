package com.senior.service;

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

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Transactional
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
	public ItemPedido findById(UUID id)  {	
		var itemPedido = itemPedidoRepository.findById(id);		
		if(itemPedido.isEmpty()) {			
			throw new EntidadeNaoEncontradaException("Não há item de pedidos cadastrados com o ID "  + id.toString());
		}
		
		return itemPedido.get();
	}
		
	public void excluir(UUID id) { 	
			ItemPedido itemPedido = findById(id);			
			itemPedidoRepository.delete(itemPedido);     
    }
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
	
	public Double calculaValorItem(Double preco,Integer quantidade) {
		return (preco * quantidade);
	}
	
	public Double aplicaDesconto(Double totalProduto,Double desconto) {
		return totalProduto-(desconto * totalProduto / 100) ;
	}
}
