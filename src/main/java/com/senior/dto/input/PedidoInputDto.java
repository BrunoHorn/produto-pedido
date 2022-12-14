package com.senior.dto.input;

import java.util.List;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class PedidoInputDto {
	
	private String observacao;
	
	@Valid
	private List<ItemPedidoInputDto> itemPedidoInputDto;
	
}
