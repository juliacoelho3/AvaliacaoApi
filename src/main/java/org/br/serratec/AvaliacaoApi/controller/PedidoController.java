package org.br.serratec.AvaliacaoApi.controller;

import java.util.List;
import java.util.Optional;

import org.br.serratec.AvaliacaoApi.dto.PedidoDto;
import org.br.serratec.AvaliacaoApi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService servico;
	
	@GetMapping
	public ResponseEntity<List<PedidoDto>> listarPedidos() {
		return ResponseEntity.ok(servico.listarTodos());	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> listarPorId(@PathVariable Long id){
		Optional<PedidoDto> pedido = servico.obterPorId(id);
		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/nome")
	public ResponseEntity<List<PedidoDto>> obterPorNome(@RequestBody String nome){
		return ResponseEntity.ok(servico.obterNome(nome));
	}
	@GetMapping("/valor/crescente")
	public ResponseEntity <List<PedidoDto>> obterPorValorCrescente(){
			return ResponseEntity.ok(servico.obterPorValorCrescente()); 
	}
	@GetMapping("/valor/abaixo")
	public ResponseEntity<List<PedidoDto>> obterPorValorAbaixo(@RequestBody String valor){
		return ResponseEntity.ok(servico.obterValorAbaixo(Double.valueOf(valor)));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDto cadastrarPedido(@Valid @RequestBody PedidoDto pedido) {
		return servico.inserirPedido(pedido);
	}
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> alterarPedido(@PathVariable Long id, @RequestBody PedidoDto pedidoAlterado){
		Optional<PedidoDto> pedido = servico.atualizarPedido(id, pedidoAlterado);
		if(pedido.isPresent()) {
		return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.notFound().build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirPedido(@PathVariable Long id){
		if(!servico.deletarPedido(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
}
