package com.senior.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.senior.enumerado.PedidoSituacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="pedido")
public class Pedido {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	private String observacao;
	
	@Column(name="data_abertura")
	private LocalDateTime dataAbertura ;
	
	@Column(name="data_fechamento")
	private LocalDateTime dataFechamento ;
	
	@Enumerated(EnumType.STRING)
	private PedidoSituacao situacao;
	 
	@Column(name="valor_total")
	private Double valorTotal;
	  
}
