package com.senior.service;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.senior.dto.ProdutoServicoDto;
import com.senior.dto.input.ProdutoServicoInputDto;
import com.senior.enumerado.TipoProdutoServico;
import com.senior.model.ProdutoServico;

public interface ProdutoServicoService {
	
	ProdutoServicoDto save(@Valid ProdutoServicoInputDto produtoServicoInputDto, UUID id);
	
	Page<ProdutoServicoDto> buscaListaprodutoServico(Boolean status,TipoProdutoServico tipo , Pageable pageable);
	
	ProdutoServico findById(UUID id);
	
	void excluir(UUID id);

}
