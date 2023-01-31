package com.example.oficinaco.jpa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Veiculo extends EntityGeneric {
	
	@ManyToOne
	private Modelo modelo;
	
	private Integer ano;
	
	private Integer anoModelo;
	
	private Integer km;
	
	private String placa;

	private String veiculoFormatado;

	public String getVeiculoFormatado() {
		return modelo.getNome() + " - " + placa;
	}

}
