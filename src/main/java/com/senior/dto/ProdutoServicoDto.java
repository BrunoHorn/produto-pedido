package com.senior.dto;

import java.util.UUID;

import com.senior.enumerado.TipoProdutoServico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class ProdutoServicoDto {
	
	private UUID id;
	
	private String nome;
	
	private Double valor;
	
	private String observacao;
	
	private TipoProdutoServico tipo;
	
	private Boolean status;

}
