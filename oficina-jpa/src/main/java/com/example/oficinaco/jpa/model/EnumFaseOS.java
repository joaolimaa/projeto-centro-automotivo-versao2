package com.example.oficinaco.jpa.model;

public enum EnumFaseOS {

	RASCUNHO("Rascunho"),
	APROVADA("Aprovada"),
	REPROVADA("Reprovada"),
	FINALIZADA("Finalizada");

	private final String descricao;

	private EnumFaseOS(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}