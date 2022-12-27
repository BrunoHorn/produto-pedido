package com.senior.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.senior.dto.ItemPedidoDto;
import com.senior.dto.PedidoDto;
import com.senior.dto.PedidoFechadoOutDto;
import com.senior.dto.input.FecharPedidoInputDto;
import com.senior.dto.input.PedidoInputDto;
import com.senior.enumerado.PedidoSituacao;
import com.senior.exception.EntidadeNaoEncontradaException;
import com.senior.exception.NegocioException;
import com.senior.mapper.ItemPedidoMapper;
import com.senior.mapper.PedidoMapper;
import com.senior.mapper.ProdutoServicoMapper;
import com.senior.model.ItemPedido;
import com.senior.model.Pedido;
import com.senior.repository.ItemPedidoRepository;
import com.senior.repository.PedidoRepository;
import com.senior.service.ItemPedidoService;
import com.senior.service.PedidoService;
import com.senior.service.ProdutoServicoService;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PedidoMapper pedidoMapper;
	
	@Autowired
	ProdutoServicoMapper produtoServicoMapper;
	
	@Autowired
	ProdutoServicoService produtoServicoService;
	
	@Autowired
	ItemPedidoService itemPedidoService; 
	
	@Autowired
	ItemPedidoMapper itemPedidoMapper;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Transactional
	@Override
	public PedidoDto save(@Valid UUID id,PedidoInputDto pedidoInputDto) {
		Pedido pedido;

		if (id == null) {
		pedido = pedidoRepository.save(Pedido.builder()
				.dataAbertura(LocalDateTime.now())
				.situacao(PedidoSituacao.ABERTO)
				.observacao(pedidoInputDto.getObservacao())
				.build());
		} else {
			pedido = findById(id);	
			if (pedido.getSituacao()== PedidoSituacao.FECHADO) {
				throw new NegocioException("Não é possível alterar pedido com situação fechado! Pedido Id :" + pedido.getId());
			}
		}
		
		List<ItemPedido>itemPedidoList = new ArrayList<>();
		for (var itemPedidoSave : pedidoInputDto.getItemPedidoInputDto()) {		
			var produtoServico = produtoServicoService.findById(itemPedidoSave.getProdutoServicoId());
			if (!produtoServico.getStatus()) {
				throw new NegocioException("Não é possível pedir produtos inativos! Produto Id " + produtoServico.getId());
			}		
			itemPedidoList.add(itemPedidoService.save(pedido,produtoServico, itemPedidoSave.getQuantidade(),itemPedidoSave.getObservacao()));
		}	
		
		PedidoDto pedidoDto =pedidoMapper.toPedidoDto(pedido);		
			
    	return pedidoDto;
	}
	
	@Override
	public Page<PedidoDto> buscaListaPedido(PedidoSituacao situacao, Pageable pageable) {
		if (situacao == null) {
			situacao =PedidoSituacao.ABERTO  ; 		
		}
		Page<Pedido> pagePedido =	pedidoRepository.findBySituacao(situacao ,pageable);		
		Page<PedidoDto> pedidoDto = pagePedido.map(pagedto -> pedidoMapper.toPedidoDto(pagedto));
					
		return pedidoDto;
	}
	@Override
	public Pedido findById(UUID id) {
		var pedidoOptional = pedidoRepository.findById(id);		
		if(pedidoOptional.isEmpty()) {			
			throw new EntidadeNaoEncontradaException("Não há pedidos cadastrados com esse ID " + id.toString());
		}		
		return pedidoOptional.get();
	}
	
	@Override
	public PedidoFechadoOutDto fecharPedido(UUID id, @Valid FecharPedidoInputDto fecharPedidoInputDto) {		
		var pedido = findById(id);	
		if(pedido.getSituacao()==PedidoSituacao.FECHADO) {
			throw new NegocioException("Pedido já está fechado, não pode ser fechado novamente");
		}
		pedido.setSituacao(PedidoSituacao.FECHADO);
		pedido.setDataFechamento(LocalDateTime.now());
		List<ItemPedido> itemPedidoList =itemPedidoRepository.findByPedido(pedido);

		var valorTotalPedido  = itemPedidoService.calcularTotal(itemPedidoList ,fecharPedidoInputDto.getDesconto());
		pedido.setValorTotal(valorTotalPedido);
		pedidoRepository.save(pedido);
		List<ItemPedidoDto> itemPedidoDto =itemPedidoMapper.toItemPedidoListDto(itemPedidoList);

		return pedidoMapper.toPedidoFechadoOutDto(pedido,itemPedidoDto);
	}

}
