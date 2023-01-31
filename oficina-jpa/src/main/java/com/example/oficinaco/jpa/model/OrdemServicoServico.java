package com.example.oficinaco.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Getter
@Setter
public class OrdemServicoServico extends EntityGeneric {

	@OneToMany(fetch= FetchType.EAGER)
	@JoinColumn(name="ordem_servico_servico_id")
	private List<Servico> servicos = new ArrayList<>();

	@ManyToOne
	private OrdemServico ordemServico;
	
	private Integer quantidade;
	
	private float preco;

	public float getTotalServicos(){
		float total = 0;

		for(Servico servico : servicos){
			total += servico.getPreco();
		}

		return total;
	}

}
