package com.example.oficinaco.jpa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Usuario extends EntityGeneric {

    private String nome;

    private String email;

    private String senha;

}
