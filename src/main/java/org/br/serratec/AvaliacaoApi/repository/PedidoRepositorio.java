package org.br.serratec.AvaliacaoApi.repository;

import java.util.List;

import org.br.serratec.AvaliacaoApi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


	public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{
		 List<Pedido> findByNomeContainingIgnoreCase(String cliente);
		 List<Pedido> findAllByOrderByValorPedidoAsc();
		 List<Pedido> findByValorPedidoLessThan(Double valor);	
	}

