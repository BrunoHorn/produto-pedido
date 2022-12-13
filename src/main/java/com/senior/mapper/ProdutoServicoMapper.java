package com.senior.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.senior.dto.ProdutoServicoDto;
import com.senior.dto.ItemPedidoDto;
import com.senior.dto.input.ProdutoServicoInputDto;
import com.senior.model.ProdutoServico;

@Component
public class ProdutoServicoMapper {
		
	public Page<ProdutoServicoDto> toProdutoServicoPageDto(Page<ProdutoServico> produtoServicoPage) {
		return produtoServicoPage.map(pagedto -> toProdutoServicoDto(pagedto));
	}
	
	public ProdutoServico toProdutoServico(ProdutoServicoInputDto produtoServicoInputDto) {
		var produtoServico = new ProdutoServico();
		 BeanUtils.copyProperties(produtoServicoInputDto, produtoServico); 
		 return produtoServico;
	}
	
	public ProdutoServicoDto toProdutoServicoDto(ProdutoServico produtoServico) {
		var produtoServicoDto = new ProdutoServicoDto();
		 BeanUtils.copyProperties(produtoServico, produtoServicoDto); 
		 return produtoServicoDto;
	}
	
	public ProdutoServico toProdutoServicoOut(ProdutoServicoDto produtoServicoDto) {
		var produtoServico = new ProdutoServico();
		 BeanUtils.copyProperties(produtoServicoDto, produtoServico); 
		 return produtoServico;
	}
	
	public ItemPedidoDto toProdutoServicoOutDto(ProdutoServico produtoServico) {
		var produtoServicoOutDto = new ItemPedidoDto();
		 BeanUtils.copyProperties( produtoServico,produtoServicoOutDto); 
		 return produtoServicoOutDto;
	}

}
