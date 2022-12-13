package com.senior.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.senior.enumerado.TipoProdutoServico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="produto_servico")
public class ProdutoServico {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	private String nome;
	
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	private TipoProdutoServico tipo;
	
	private String observacao;
	
	private Boolean status;
	
}
