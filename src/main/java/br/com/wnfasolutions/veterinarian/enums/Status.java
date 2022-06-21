package br.com.wnfasolutions.veterinarian.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
	PENDENTE("Pendente"),
	CANCELADO("Cancelado"),
	FINALIZADO("Finalizado");

	private String descricao;
}