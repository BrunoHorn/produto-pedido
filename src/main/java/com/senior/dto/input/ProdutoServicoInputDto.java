package com.senior.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.senior.enumerado.TipoProdutoServico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoServicoInputDto {

	@NotBlank
	@Size(max =50)
	private String nome;
	
	private Double valor;
	
	private String observacao;
	
	private TipoProdutoServico tipo;
		
	private Boolean status;
}
