package com.example.oficinaco.jpa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Modelo extends EntityGeneric {
	
	private String nome;

	@ManyToOne
	private Marca marca;

}
