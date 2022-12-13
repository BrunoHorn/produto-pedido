package com.senior.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senior.dto.ItemPedidoDto;
import com.senior.dto.input.ItemPedidoInputDto;
import com.senior.model.ItemPedido;

@Component
public class ItemPedidoMapper {
	
	@Autowired
	PedidoMapper pedidoMapper;
	
	@Autowired
	ProdutoServicoMapper produtoServicoMapper;
	
	public ItemPedido toItemPedido(ItemPedidoInputDto itemPedidoInputDto) {
		var itemPedido = new ItemPedido();
		 BeanUtils.copyProperties(itemPedidoInputDto, itemPedido); 
		 return itemPedido;
	}
	
	public List<ItemPedidoDto> toItemPedidoListDto(List<ItemPedido> itemPedido){
		List <ItemPedidoDto> produtoServicoOutListDto = new ArrayList<>();
		for (ItemPedido item : itemPedido) {
			var produtoServicoOutDto = new ItemPedidoDto();
			produtoServicoOutDto.setId(item.getId());
			produtoServicoOutDto.setNome(item.getProdutoServico().getNome());
			produtoServicoOutDto.setQuantidade(item.getQuantidade());
			produtoServicoOutDto.setObservacao(item.getObservacao());
			produtoServicoOutListDto.add(produtoServicoOutDto);			
		}		
		
		return produtoServicoOutListDto;
	}


}
