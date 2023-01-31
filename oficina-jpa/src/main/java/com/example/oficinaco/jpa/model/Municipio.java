package com.example.oficinaco.jpa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Municipio extends EntityGeneric {
	
	private String nome;

	@Enumerated(EnumType.STRING)
	private EnumUf uf;
	
	private Integer codigoIbge;

}
