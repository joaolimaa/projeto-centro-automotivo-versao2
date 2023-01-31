package com.example.oficinaco.jpa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pessoa extends EntityGeneric {
	
	private String nome;
	
	private String cpf;
	
	private String endereco;
	
	private String bairro;
	
	private String telefone;
	
	private boolean whatsapp;
	
	private String cep;
	
	private boolean funcionario;
	
	@ManyToOne
	private Municipio municipio;

}
