package com.senior.dto.input;

import java.util.UUID;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class ItemPedidoInputPutDto {

	private UUID ProdutoServicoId;
	
	@Min(1)
	private Integer quantidade;
	
	private String observacao;

}
