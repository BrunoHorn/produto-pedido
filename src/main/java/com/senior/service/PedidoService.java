package com.senior.service;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.senior.dto.PedidoDto;
import com.senior.dto.PedidoFechadoOutDto;
import com.senior.dto.input.FecharPedidoInputDto;
import com.senior.dto.input.PedidoInputDto;
import com.senior.enumerado.PedidoSituacao;
import com.senior.model.Pedido;

public interface PedidoService {
	
	PedidoDto save(@Valid UUID id,PedidoInputDto pedidoInputDto);
	
	Page<PedidoDto> buscaListaPedido(PedidoSituacao situacao, Pageable pageable);
	
	Pedido findById(UUID id);
	
	PedidoFechadoOutDto fecharPedido(UUID id, @Valid FecharPedidoInputDto fecharPedidoInputDto);
	
}
