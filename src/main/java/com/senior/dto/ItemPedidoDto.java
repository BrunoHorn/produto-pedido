package com.senior.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class ItemPedidoDto {

	private UUID id;
	
	private  String nome;
		
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private  String observacao;
	
	private Integer quantidade;

}
