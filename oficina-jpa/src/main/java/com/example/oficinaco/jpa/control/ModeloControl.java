package com.example.oficinaco.jpa.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.oficinaco.jpa.dao.MarcaDao;
import com.example.oficinaco.jpa.dao.ModeloDao;
import com.example.oficinaco.jpa.model.Marca;
import com.example.oficinaco.jpa.model.Modelo;
import com.example.oficinaco.jpa.util.converters.EntityConverter;

@Component
@ViewScoped
public class ModeloControl {
	
	@Autowired
	private ModeloDao modeloDao;
	
	private Modelo modelo = new Modelo();
	
	private List<Modelo> modelos = new ArrayList<>();

	@Autowired
	private MarcaDao marcaDao;

	private EntityConverter entityConverter = new EntityConverter();
	
	@PostConstruct
	public void init() {
		listar();
	}
	
	public void salvar() {
		String mensagem = null;
		try {
			mensagem = "Modelo alterado com sucesso!";
			if (modelo.getId() == null) {
				mensagem = "Modelo salvo com sucesso!";
				List<Modelo> modelos = modeloDao.listarPorNome(modelo.getNome());
				if (!modelos.isEmpty()) {
					mensagem = "Já existe um modelo com esse nome!";
					throw new Exception();
				}
			}
			modeloDao.save(modelo);
			modelo = new Modelo();
			listar();
			Messages.addGlobalInfo(mensagem);
		} catch (Exception e) {
			Messages.addGlobalError(mensagem);
		}
	}
	
	public void listar() {
		modelos = modeloDao.findAll();
	}
	
	public void excluir(Integer id) {
		modeloDao.deleteById(id);
		listar();
		Messages.addGlobalInfo("Modelo excluido com sucesso!");
	}

	public List<Marca> completeMarca(String query) {
		return marcaDao.listarPorNome("%" + query + "%");
	}

	public void limpar(){
		this.modelo = new Modelo();
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
		Messages.addGlobalInfo("Modelo alterado com sucesso");
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public EntityConverter getEntityConverter() {
		return entityConverter;
	}

	public void setEntityConverter(EntityConverter entityConverter) {
		this.entityConverter = entityConverter;
	}

}














