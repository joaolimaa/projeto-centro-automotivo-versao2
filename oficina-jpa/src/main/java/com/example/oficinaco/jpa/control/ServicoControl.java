package com.example.oficinaco.jpa.control;

import com.example.oficinaco.jpa.dao.ServicoDao;
import com.example.oficinaco.jpa.model.Modelo;
import com.example.oficinaco.jpa.model.Produto;
import com.example.oficinaco.jpa.model.Servico;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScoped
public class ServicoControl {
	
	@Autowired
	private ServicoDao servicoDao;
	
	private Servico servico = new Servico();
	
	private static List<Servico> servicos = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		listar();
	}

	public void limpar(){
		this.servico = new Servico();
	}
	
	public void salvar() {
		String mensagem = null;
		try {
			mensagem = "Serviço alterado com sucesso!";
			if (servico.getId() == null) {
				mensagem = "Serviço salvo com sucesso!";
				List<Servico> servicos = servicoDao.listarPorNome(servico.getNome());
				if (!servicos.isEmpty()) {
					mensagem = "Já existe um serviço com esse nome!";
					throw new Exception();
				}
			}
			servicoDao.save(servico);
			servico = new Servico();
			listar();
			Messages.addGlobalInfo(mensagem);
		} catch (Exception e) {
			Messages.addGlobalError(mensagem);
		}
	}
	
	public void listar() {
		servicos = servicoDao.findAll();
	}
	
	public void excluir(Integer id) {
		servicoDao.deleteById(id);
		listar();
		Messages.addGlobalInfo("Serviço excluido com sucesso!");
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
		Messages.addGlobalInfo("Serviço alterado com sucesso");
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public static Servico getServicoById(String value){
		return servicos.stream().filter(e -> e.getId().equals(Integer.parseInt(value))).findAny().orElse(null);
	}
	
}














