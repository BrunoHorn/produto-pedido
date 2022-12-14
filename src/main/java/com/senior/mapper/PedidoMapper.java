package com.senior.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senior.dto.ItemPedidoDto;
import com.senior.dto.PedidoDto;
import com.senior.dto.PedidoFechadoOutDto;
import com.senior.model.ItemPedido;
import com.senior.model.Pedido;
import com.senior.repository.ItemPedidoRepository;

@Component
public class PedidoMapper {
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public PedidoDto toPedidoDto(Pedido pedido) {
		
		var pedidoDto = new PedidoDto();
		pedidoDto.setId(pedido.getId());
		pedidoDto.setDataPedido(pedido.getDataAbertura());
		pedidoDto.setObservacao(pedido.getObservacao());
		pedidoDto.setSituacao(pedido.getSituacao());
		
		List<ItemPedido> itempedido = itemPedidoRepository.findByPedido(pedido);
		List<ItemPedidoDto> listItemPedidoDto = new ArrayList<>();
		for (ItemPedido umitempedido : itempedido) {
			var	produtoServicoOutDto = new ItemPedidoDto();
			produtoServicoOutDto.setId(umitempedido.getProdutoServico().getId());
			produtoServicoOutDto.setNome(umitempedido.getProdutoServico().getNome());
			produtoServicoOutDto.setQuantidade(umitempedido.getQuantidade());
			listItemPedidoDto.add(produtoServicoOutDto);				
		}		
		pedidoDto.setItemPedidoDto(listItemPedidoDto);
		
		return pedidoDto;
	}	
	
	public PedidoFechadoOutDto toPedidoFechadoOutDto (Pedido pedido,List<ItemPedidoDto> itemPedidoDtoList) {
		
		PedidoFechadoOutDto pedidoFechadoOutDto = new PedidoFechadoOutDto();
		BeanUtils.copyProperties(pedido, pedidoFechadoOutDto); 
		pedidoFechadoOutDto.setItemPedidoDto(itemPedidoDtoList);
	
		return pedidoFechadoOutDto;
	}
}
