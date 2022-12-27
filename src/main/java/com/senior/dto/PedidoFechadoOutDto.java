package com.senior.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senior.enumerado.PedidoSituacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class PedidoFechadoOutDto {   

	private UUID id;
		
	private String observacao;
			
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataAbertura;
		
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataFechamento;
	
	private PedidoSituacao situacao;
		 
	private List<ItemPedidoDto> itemPedidoDto;
	
	private Double valorTotal;
		
}
