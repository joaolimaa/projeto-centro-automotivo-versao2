package com.example.oficinaco.jpa.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Marca extends EntityGeneric {

    private String nome;

}
