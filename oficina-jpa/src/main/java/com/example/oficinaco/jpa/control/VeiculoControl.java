package com.example.oficinaco.jpa.control;

import com.example.oficinaco.jpa.dao.ModeloDao;
import com.example.oficinaco.jpa.dao.VeiculoDao;
import com.example.oficinaco.jpa.model.Modelo;
import com.example.oficinaco.jpa.model.Veiculo;
import com.example.oficinaco.jpa.util.converters.EntityConverter;
import com.example.oficinaco.jpa.util.validadores.Validadores;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScoped
public class VeiculoControl {
	
	@Autowired
	private VeiculoDao veiculoDao;
	
	private Veiculo veiculo = new Veiculo();
	
	private List<Veiculo> veiculos = new ArrayList<>();

	@Autowired
	private ModeloDao modeloDao;

	private EntityConverter entityConverter = new EntityConverter();
	
	@PostConstruct
	public void init() {
		listar();
	}
	
	public void salvar() {
		String mensagem = null;
		try{
			mensagem = "Veículo alterado com sucesso!";
			if (veiculo.getId() == null) {
				if (!Validadores.validaPlaca(veiculo.getPlaca())){

				}
				mensagem = "Veículo salvo com sucesso!";
			}
			veiculo.setVeiculoFormatado(veiculo.getVeiculoFormatado());
			veiculoDao.save(veiculo);
			veiculo = new Veiculo();
			listar();
			Messages.addGlobalInfo(mensagem);
		} catch (Exception e){
			Messages.addGlobalError(mensagem);
		}
	}
	
	public void listar() {
		veiculos = veiculoDao.findAll();
	}
	
	public void excluir(Integer id) {
		veiculoDao.deleteById(id);
		listar();
		Messages.addGlobalInfo("Veículo excluido com sucesso!");
	}

	public List<Modelo> completeModelo(String query) {
		return modeloDao.listarPorNome("%" + query + "%");
	}

	public void limpar(){
		this.veiculo = new Veiculo();
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
		Messages.addGlobalInfo("Veículo alterado com sucesso");
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public EntityConverter getEntityConverter() {
		return entityConverter;
	}

	public void setEntityConverter(EntityConverter entityConverter) {
		this.entityConverter = entityConverter;
	}

}














