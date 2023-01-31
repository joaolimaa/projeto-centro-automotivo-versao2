package com.example.oficinaco.jpa.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import com.example.oficinaco.jpa.dao.PessoaDao;
import com.example.oficinaco.jpa.dao.VeiculoDao;
import com.example.oficinaco.jpa.model.*;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.oficinaco.jpa.dao.OrdemServicoDao;
import com.example.oficinaco.jpa.util.converters.EntityConverter;
import com.example.oficinaco.jpa.util.converters.OrdemServicoProdutoConverter;
import com.example.oficinaco.jpa.util.converters.ProdutoConverter;
import com.example.oficinaco.jpa.util.converters.ServicoConverter;
import com.example.oficinaco.jpa.util.enums.FaseOS;

@Component
@SessionScoped
public class OrdemServicoControl {

	@Autowired
	private OrdemServicoDao ordemServicoDao;

	@Autowired
	private PessoaDao pessoaDao;

	@Autowired
	private VeiculoDao veiculoDao;

	private OrdemServico ordemServico = new OrdemServico();

	private static List<OrdemServico> ordemServicos = new ArrayList<>();

	private List<Produto> produtos = new ArrayList<>();

	private List<Servico> servicos = new ArrayList<>();

	private EntityConverter entityConverter = new EntityConverter();

	private ProdutoConverter produtoConverter = new ProdutoConverter();

	private ServicoConverter servicoConverter = new ServicoConverter();

	private OrdemServicoProdutoConverter ordemServicoProdutoConverter = new OrdemServicoProdutoConverter();

	@PostConstruct
	public void init(){
		listar();
	}

	public void limpar(){
		this.ordemServico = new OrdemServico();
	}

	public void salvar(){
		String mensagem = "Ordem de serviço alterada com sucesso!";
		if (ordemServico.getId() == null) {
			mensagem = "Ordem de serviço salva com sucesso!";
		}
		ordemServico.setFaseOS(FaseOS.RASCUNHO);
		ordemServicoDao.save(ordemServico);
		ordemServico = new OrdemServico();
		listar();
		Messages.addGlobalInfo(mensagem);
	}

	public void listar(){
		ordemServicos = ordemServicoDao.findAll();
	}

	public void excluir(Integer id){
		ordemServicoDao.deleteById(id);
		listar();
		Messages.addGlobalInfo("Ordem de serviço excluida com sucesso!");
	}

	public List<Pessoa> completeFuncionario(String query) {
		return pessoaDao.listarFuncionarioPorNome("%" + query + "%");
	}

	public List<Pessoa> completeCliente(String query) {
		return pessoaDao.listarClientePorNome("%" + query + "%");
	}

	public List<Veiculo> completeVeiculo(String query) {
		return veiculoDao.listarPorModelo("%" + query + "%");
	}

	public OrdemServicoDao getOrdemServicoDao() {
		return ordemServicoDao;
	}

	public void setOrdemServicoDao(OrdemServicoDao ordemServicoDao) {
		this.ordemServicoDao = ordemServicoDao;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
		Messages.addGlobalInfo("Ordem de serviço alterada com sucesso");
	}

	public List<OrdemServico> getOrdemServicos() {
		return ordemServicos;
	}

	public void setOrdemServicos(List<OrdemServico> ordemServicos) {
		this.ordemServicos = ordemServicos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public EntityConverter getEntityConverter() {
		return entityConverter;
	}

	public void setEntityConverter(EntityConverter entityConverter) {
		this.entityConverter = entityConverter;
	}

	public ProdutoConverter getProdutoConverter() {
		return produtoConverter;
	}

	public void setProdutoConverter(ProdutoConverter produtoConverter) {
		this.produtoConverter = produtoConverter;
	}

	public ServicoConverter getServicoConverter() {
		return servicoConverter;
	}

	public void setServicoConverter(ServicoConverter servicoConverter) {
		this.servicoConverter = servicoConverter;
	}

	public OrdemServicoProdutoConverter getOrdemServicoProdutoConverter() {
		return ordemServicoProdutoConverter;
	}

	public void setOrdemServicoProdutoConverter(OrdemServicoProdutoConverter ordemServicoProdutoConverter) {
		this.ordemServicoProdutoConverter = ordemServicoProdutoConverter;
	}

	public static OrdemServico getOrdemServicoById(String value){
		return ordemServicos.stream().filter(e -> e.getId().equals(Integer.parseInt(value))).findAny().orElse(null);
	}

	public void vigenciarOS(OrdemServico ordemServico){
		ordemServico.setFaseOS(FaseOS.VIGENTE);
		ordemServicoDao.save(ordemServico);
		Messages.addGlobalInfo("Ordem de Serviço Vigenciada com sucesso!");
	}

	public boolean ocultarBotaoNaFaseVigente(OrdemServico ordemServico) {
		return ordemServico.getFaseOS().equals(FaseOS.VIGENTE) || ordemServico.getFaseOS().equals(FaseOS.OBSOLETA);
	}

	public void obsoletarOS(OrdemServico ordemServico){
		ordemServico.setFaseOS(FaseOS.OBSOLETA);
		ordemServicoDao.save(ordemServico);
		Messages.addGlobalInfo("Ordem de Serviço Obsoletada com sucesso!");
	}

	public boolean ocultarBotaoNaFaseObsoleta(OrdemServico ordemServico) {
		return ordemServico.getFaseOS().equals(FaseOS.OBSOLETA) || ordemServico.getFaseOS().equals(FaseOS.RASCUNHO);
	}

	public boolean ocultarBotaoEditar(OrdemServico ordemServico) {
		return !ordemServico.getFaseOS().equals(FaseOS.RASCUNHO);
	}

	public boolean ocultarBotaoExcluir(OrdemServico ordemServico){
		return ordemServico.getFaseOS().equals(FaseOS.VIGENTE);
	}

	public void salvarEntidade(OrdemServico ordemServico){
		ordemServico.setFaseOS(FaseOS.RASCUNHO);
		ordemServicoDao.save(ordemServico);
	}

}
