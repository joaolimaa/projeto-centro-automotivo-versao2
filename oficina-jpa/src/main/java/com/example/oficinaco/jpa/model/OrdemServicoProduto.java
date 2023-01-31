package com.example.oficinaco.jpa.model;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrdemServicoProduto extends EntityGeneric {
	
	@ManyToOne(optional = false)
	private Produto produto;
	
	private Integer quantidade;
	
	private float preco;

	@ManyToOne
	private OrdemServico ordemServico;

	public float getTotalProdutos(){
		return produto.getPrecoVenda() * getQuantidade();
	}

}
