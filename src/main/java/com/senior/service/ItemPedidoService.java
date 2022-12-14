package com.senior.service;

import java.util.List;
import java.util.UUID;

import com.senior.model.ItemPedido;
import com.senior.model.Pedido;
import com.senior.model.ProdutoServico;

public interface ItemPedidoService  {

	ItemPedido save(Pedido pedido,ProdutoServico produtoServico, Integer quantidade, String observacao);
	
	ItemPedido findById(UUID id);
	
	void excluir(UUID id);
	
	Double calcularTotal(List<ItemPedido> itemPedidoList,Double desconto);
	
}
