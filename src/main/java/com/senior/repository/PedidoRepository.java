package com.senior.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senior.enumerado.PedidoSituacao;
import com.senior.model.Pedido;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido,UUID>{

	Page<Pedido> findBySituacao(PedidoSituacao situacao, Pageable pageable);

}
