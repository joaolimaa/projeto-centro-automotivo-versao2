package com.example.oficinaco.jpa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.example.oficinaco.jpa.util.enums.FaseOS;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrdemServico extends EntityGeneric {

	private EnumFaseOS fase = EnumFaseOS.RASCUNHO;
	
	@ManyToOne(optional = false)
	private Pessoa cliente;
	
	@ManyToOne(optional = false)
	private Pessoa funcionario;
	
	@ManyToOne(optional = false)
	private Veiculo veiculo;
	
	@ManyToOne
	@JoinColumn(name="ordem_servico_id")
	private OrdemServicoServico servicos;

	@OneToMany
	@JoinColumn(name="ordem_produto_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<OrdemServicoProduto> produtos = new ArrayList<>();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataOs;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicioServico;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimServico;
	
	private float desconto;

	@Column(nullable = true)
	private float total = 0;

	private FaseOS faseOS;

	public float getTotal() {
		return getValorTotalOrdemServico();
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getValorTotalOrdemServico(){
		float total = 0;

		for(OrdemServicoProduto produto : produtos){
			total += produto.getTotalProdutos();
		}

		if (servicos != null) {
			total += servicos.getTotalServicos();
		}

		return total - getDesconto();
	}
}
