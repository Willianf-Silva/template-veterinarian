package br.com.wnfasolutions.veterinarian.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Situation {
	ATIVO("Ativo"),
	INATIVO("Inativo");

	private String descricao;
}