package com.senior.repository;

import java.util.List;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;

import com.senior.model.ItemPedido;
import com.senior.model.Pedido;
import com.senior.model.ProdutoServico;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {

	ItemPedido findByProdutoServicoAndPedido(ProdutoServico produtoServico , Pedido pedido);
	
	List<ItemPedido> findByPedido(Pedido pedido);
	
}
