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

import com.senior.dto.ProdutoServicoDto;
import com.senior.dto.input.ProdutoServicoInputDto;
import com.senior.enumerado.TipoProdutoServico;
import com.senior.mapper.ProdutoServicoMapper;
import com.senior.service.ProdutoServicoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/produto-servico")
@RestController
@Api(value="Cadastro Produto e serviços")
public class ProdutoServicoController {
	
	@Autowired
	ProdutoServicoService produtoServicoService;
	
	@Autowired
	ProdutoServicoMapper produtoServicoMapper;
	
	@PostMapping
	@ApiOperation(value="Cadastra novo produto ou serviço")
    public ResponseEntity<ProdutoServicoDto> saveProdutoServico(@RequestBody @Valid ProdutoServicoInputDto produtoServicoInputDto){		
       
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoServicoService.save(produtoServicoInputDto, null));
    }
	
    @GetMapping
    @ApiOperation(value="Busca lista de produtos e serviços cadastrados")
    public ResponseEntity<Page<ProdutoServicoDto>> getListProdutoServico(@RequestParam(required = false, defaultValue = "true") Boolean status,    		
    		@RequestParam(required = false)TipoProdutoServico tipo,
    		@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){      

    	Page<ProdutoServicoDto> listaProdutoServicoDto = produtoServicoService.buscaListaprodutoServico(status,tipo, pageable);

    	return ResponseEntity.status(HttpStatus.OK).body(listaProdutoServicoDto);               
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value="Busca produto ou serviço cadastrado pelo ID")
    public ResponseEntity<ProdutoServicoDto> getProdutoServicoid(@PathVariable(value = "id") UUID id) throws Exception {
    	
    	ProdutoServicoDto produtoServicoDto =produtoServicoMapper.toProdutoServicoDto(produtoServicoService.findById(id));   	
        
    	return ResponseEntity.status(HttpStatus.OK).body(produtoServicoDto);
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza Produto ou serviço cadastrado pelo ID")
    public ResponseEntity<ProdutoServicoDto> updatProdutoServico(@PathVariable(value = "id")UUID id,
    		@RequestBody @Valid ProdutoServicoInputDto produtoServicoInputDto) throws Exception{
    	
    	ProdutoServicoDto produtoServicoDto =produtoServicoMapper.toProdutoServicoDto(produtoServicoService.findById(id));  
        
    	return ResponseEntity.status(HttpStatus.OK).body(produtoServicoService.save(produtoServicoInputDto, produtoServicoDto.getId()));
    }
	
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoServicoDto> deleta(@PathVariable(value = "id") UUID id) throws Exception{   			
    	
    	produtoServicoService.excluir(id);
    	
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();   		 		   		
    }
		
}
