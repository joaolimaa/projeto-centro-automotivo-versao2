package com.example.oficinaco.jpa.model;

import java.util.Objects;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto extends EntityGeneric {
	
	private String nome;
	
	private float precoVenda;

	private int quantidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof Produto))
			return false;
		Produto other = (Produto) obj;
		if(getId() == null){
			if(other.getId() != null)
				return false;
		} else if(!getId().equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, precoVenda, quantidade);
	}
}
