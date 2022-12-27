package com.senior.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senior.dto.PedidoDto;
import com.senior.dto.PedidoFechadoOutDto;
import com.senior.dto.input.FecharPedidoInputDto;
import com.senior.dto.input.PedidoInputDto;
import com.senior.enumerado.PedidoSituacao;
import com.senior.mapper.PedidoMapper;
import com.senior.model.Pedido;
import com.senior.service.ItemPedidoService;
import com.senior.service.PedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/pedido")
@RestController
@Api(value="Cadastro pedido")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ItemPedidoService itemPedidoService;
	
	@Autowired
	PedidoMapper pedidoMapper;
		
	@PostMapping("/abrir")
	@ApiOperation(value="Cadastra novo Pedido")
    public ResponseEntity<PedidoDto> savePedido(@RequestBody @Valid PedidoInputDto pedidoInputDto){		
        
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(null,pedidoInputDto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza informações do pedido e adiciona/altera itens do pedido")
    public ResponseEntity<PedidoDto> updatePedido(@PathVariable(value = "id")UUID id,
    		@RequestBody @Valid PedidoInputDto pedidoInputDto){
    	 
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(id,pedidoInputDto));
    }
    
    @DeleteMapping("/item-pedido/{id}")
    @ApiOperation(value="Remover um item do pedido")
    public ResponseEntity<PedidoDto> updateRemoverItemPedido(@PathVariable(value = "id") UUID id) throws Exception {	 
    	
    	itemPedidoService.excluir(id);
        
    	return  ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
    }
    
    @PutMapping("/{id}/fechar")
    @ApiOperation(value="Pega o desconto e finaliza o pedido")
    public ResponseEntity<PedidoFechadoOutDto> fecharPedido(@PathVariable(value = "id")UUID id,
    		@RequestBody @Valid FecharPedidoInputDto fecharPedidoInputDto){
    	 
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.fecharPedido(id,fecharPedidoInputDto));
    }
	
    @GetMapping
    @ApiOperation(value="Busca lista de pedidos cadastrados")
    public ResponseEntity<Page<PedidoDto>> getListPedido(@RequestParam(required = false,defaultValue ="ABERTO")PedidoSituacao situacao ,
    		@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable	){
        
        Page<PedidoDto> listaPedidoDto = pedidoService.buscaListaPedido(situacao, pageable);
        
        return ResponseEntity.status(HttpStatus.OK).body(listaPedidoDto);               
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value="Busca pedido cadastrado pelo ID")
    public ResponseEntity<PedidoDto> getPedidoid(@PathVariable(value = "id") UUID id) {
    	
    	Pedido pedido =pedidoService.findById(id);  
    	
        return ResponseEntity.status(HttpStatus.OK).body(pedidoMapper.toPedidoDto(pedido));
    }

}
