package com.senior.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senior.enumerado.TipoProdutoServico;
import com.senior.model.ProdutoServico;

@Repository
public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, UUID> {

	Page<ProdutoServico>findByStatusAndTipo(Boolean status,TipoProdutoServico tipo ,Pageable pageable);
	
	Page<ProdutoServico>findByStatus(Boolean status, Pageable pageable);

}
