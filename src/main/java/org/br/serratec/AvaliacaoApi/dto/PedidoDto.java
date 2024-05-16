package org.br.serratec.AvaliacaoApi.dto;

import org.br.serratec.AvaliacaoApi.model.Pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PedidoDto (
		Long id,
		@NotBlank(message = "Este campo não pode ser vazio...")
		String nome,
		@NotBlank(message = "Este campo não pode ser vazio...")
		String prato,
		@NotNull(message = "Digite um valor valido...")
		@Positive
		Double valorPedido){
	
	public Pedido toEntity() {
		return new Pedido (this.id, this.nome, this.prato, this.valorPedido);
	}
}
