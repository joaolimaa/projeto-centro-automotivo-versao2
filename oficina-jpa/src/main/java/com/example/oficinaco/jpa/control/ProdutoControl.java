package com.example.oficinaco.jpa.control;

import com.example.oficinaco.jpa.dao.ProdutoDao;
import com.example.oficinaco.jpa.model.Modelo;
import com.example.oficinaco.jpa.model.Produto;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ProdutoControl {

	@Autowired
	private ProdutoDao produtoDao;

	private Produto produto = new Produto();
	
	private static List<Produto> produtos = new ArrayList<>();

	@PostConstruct
	public void init() {
		listar();
	}

	public void limpar(){
		this.produto = new Produto();
	}
	
	public void salvar() {
		String mensagem = null;
		try {
			mensagem = "Produto alterado com sucesso!";
			if (produto.getId() == null) {
				mensagem = "Produto salvo com sucesso!";
				List<Produto> produtos = produtoDao.listarPorNome(produto.getNome());
				if (!produtos.isEmpty()) {
					mensagem = "Já existe um produto com esse nome!";
					throw new Exception();
				}
			}
			produtoDao.save(produto);
			produto = new Produto();
			listar();
			Messages.addGlobalInfo(mensagem);
		} catch (Exception e) {
			Messages.addGlobalError(mensagem);
		}
	}
	
	public void listar() {
		produtos = produtoDao.findAll();
	}

	public void excluir(Integer id) {
		produtoDao.deleteById(id);
		listar();
		Messages.addGlobalInfo("Produto excluido com sucesso!");
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
		Messages.addGlobalInfo("Produto alterado com sucesso");
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public static Produto getProdutoById(String value){
		return produtos.stream().filter(e -> e.getId().equals(Integer.parseInt(value))).findAny().orElse(null);
	}

}
