package com.senior.service.impl;

import java.util.Objects;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.senior.dto.ProdutoServicoDto;
import com.senior.dto.input.ProdutoServicoInputDto;
import com.senior.enumerado.PedidoSituacao;
import com.senior.enumerado.TipoProdutoServico;
import com.senior.exception.EntidadeEmUsoException;
import com.senior.exception.EntidadeNaoEncontradaException;
import com.senior.exception.NegocioException;
import com.senior.mapper.ProdutoServicoMapper;
import com.senior.model.ProdutoServico;
import com.senior.repository.ProdutoServicoRepository;
import com.senior.service.ProdutoServicoService;

@Service
public class ProdutoServicoServiceImpl implements ProdutoServicoService {

	@Autowired
	ProdutoServicoRepository produtoServicoRepository;
	
	@Autowired
	ProdutoServicoMapper produtoServicoMapper;
	
	@Transactional
	@Override
	public ProdutoServicoDto save(@Valid ProdutoServicoInputDto produtoServicoInputDto, UUID id) {		
		if (id == null){
		validaImput(produtoServicoInputDto);
		}
		var produtoServico = produtoServicoMapper.toProdutoServico(produtoServicoInputDto);
        if (Objects.nonNull(id))  {     	
        	produtoServico.setId(id);
        }			
		produtoServico = produtoServicoRepository.save(produtoServico);
		ProdutoServicoDto produtoServicoDto =produtoServicoMapper.toProdutoServicoDto(produtoServico);
		
		return produtoServicoDto;
	}
	
	@Override
	public Page<ProdutoServicoDto> buscaListaprodutoServico(Boolean status,TipoProdutoServico tipo , Pageable pageable) {		
		if (tipo == null) {
			return produtoServicoMapper.toProdutoServicoPageDto(produtoServicoRepository.findByStatus(status, pageable));	
		}
		
		return produtoServicoMapper.toProdutoServicoPageDto(produtoServicoRepository.findByStatusAndTipo(status,tipo, pageable));		
	}
	
	@Override
	public ProdutoServico findById(UUID id)  {	
		var produtoServico = produtoServicoRepository.findById(id);		
			if(produtoServico.isEmpty()) {			
			throw new EntidadeNaoEncontradaException("Não a produtos ou serviços cadastrados com esse ID");
		}
				
		return produtoServico.get();
	}
	
	@Override
	public void excluir(UUID id) {
		try { 	
			ProdutoServico produtoServico = findById(id);			
			produtoServicoRepository.delete(produtoServico);     
		} catch(DataIntegrityViolationException e){
		throw new EntidadeEmUsoException("Produto ou Servico está em uso , só pode ser desativado");
		}
    }
	
	private void validaImput (ProdutoServicoInputDto produtoServicoInputDto) {
		if((produtoServicoInputDto.getTipo() == null)) {
			throw new NegocioException("Tipo não pode estar vazio. Deve ser um PRODUTO ou SERVICO");
		}
	}
	
}


