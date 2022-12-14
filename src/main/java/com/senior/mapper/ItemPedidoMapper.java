package com.senior.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senior.dto.ItemPedidoDto;
import com.senior.model.ItemPedido;

@Component
public class ItemPedidoMapper {
	
	@Autowired
	PedidoMapper pedidoMapper;
	
	@Autowired
	ProdutoServicoMapper produtoServicoMapper;
	
	public List<ItemPedidoDto> toItemPedidoListDto(List<ItemPedido> itemPedido){
		
		List <ItemPedidoDto> produtoServicoOutListDto = new ArrayList<>();
		for (ItemPedido item : itemPedido) {
			var itemPedidoDto = new ItemPedidoDto();
			itemPedidoDto.setId(item.getId());
			itemPedidoDto.setNome(item.getProdutoServico().getNome());
			itemPedidoDto.setQuantidade(item.getQuantidade());
			itemPedidoDto.setObservacao(item.getObservacao());
			produtoServicoOutListDto.add(itemPedidoDto);			
		}		
		
		return produtoServicoOutListDto;
	}
}
