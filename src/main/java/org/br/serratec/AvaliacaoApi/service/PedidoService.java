package org.br.serratec.AvaliacaoApi.service;

import java.util.List;
import java.util.Optional;

import org.br.serratec.AvaliacaoApi.dto.PedidoDto;
import org.br.serratec.AvaliacaoApi.model.Pedido;
import org.br.serratec.AvaliacaoApi.repository.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class PedidoService {

	@Autowired
	public PedidoRepositorio pedidoRepositorio;
	
	
	public List<PedidoDto> listarTodos(){
		return pedidoRepositorio.findAll().stream()
				.map(p -> new PedidoDto(p.getId(), p.getNome(),
							  p.getPrato(), p.getValorPedido())).toList();
	}


	public Optional<PedidoDto> obterPorId(Long id){
		Optional<Pedido> pedido = pedidoRepositorio.findById(id);
		if(pedido.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(pedido.get().toDto());
	}


	public List<PedidoDto> obterNome(String nome) {
		return pedidoRepositorio.findByNomeContainingIgnoreCase(nome).stream()
				.map(p -> new PedidoDto(p.getId(), p.getNome(),
							  p.getPrato(), p.getValorPedido())).toList();
	}
	
	public List<PedidoDto> obterPorValorCrescente() {
		return pedidoRepositorio.findAllByOrderByValorPedidoAsc().stream()
				.map(p -> new PedidoDto(p.getId(), p.getNome(),
							  p.getPrato(), p.getValorPedido())).toList();
	
	}


	public List<PedidoDto> obterValorAbaixo(Double valor) {
		return pedidoRepositorio.findByValorPedidoLessThan(valor).stream()
				.map(p -> new PedidoDto(p.getId(), p.getNome(),
							  p.getPrato(), p.getValorPedido())).toList();
	}


	public PedidoDto inserirPedido(PedidoDto pedido) {
		Pedido pedidoEntity = pedidoRepositorio.save(pedido.toEntity());
		return pedidoEntity.toDto();
		
	}


	public Optional<PedidoDto> atualizarPedido(Long id, PedidoDto pedido){
		Pedido pedidoEntity = pedido.toEntity();
		if(pedidoRepositorio.existsById(id)) {
			pedidoEntity.setId(id);
			pedidoRepositorio.save(pedidoEntity);
			return Optional.of(pedidoEntity.toDto());
		}
		return Optional.empty();
	}


	public boolean deletarPedido(Long id) {
		if(!pedidoRepositorio.existsById(id)) {
			return false;
		}
		pedidoRepositorio.deleteById(id);
		return true;
	}

}
