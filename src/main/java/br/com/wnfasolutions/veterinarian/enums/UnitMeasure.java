package br.com.wnfasolutions.veterinarian.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnitMeasure {
	UNIDADE("Unidade"),
	SERVICO("Serviço");

	private String descricao;
}