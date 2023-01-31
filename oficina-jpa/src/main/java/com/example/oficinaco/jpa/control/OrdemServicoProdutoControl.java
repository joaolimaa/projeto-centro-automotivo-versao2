package com.example.oficinaco.jpa.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import com.example.oficinaco.jpa.dao.OrdemServicoDao;
import com.example.oficinaco.jpa.dao.ProdutoDao;
import com.example.oficinaco.jpa.model.OrdemServico;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.oficinaco.jpa.dao.OrdemServicoProdutoDao;
import com.example.oficinaco.jpa.model.OrdemServicoProduto;
import com.example.oficinaco.jpa.model.Produto;
import com.example.oficinaco.jpa.util.converters.EntityConverter;
import com.example.oficinaco.jpa.util.converters.ProdutoConverter;

@Component
@SessionScoped
public class OrdemServicoProdutoControl {

    @Autowired
    private OrdemServicoProdutoDao ordemServicoProdutoDao;

    @Autowired
    private OrdemServicoDao ordemServicoDao;

    @Autowired
    private ProdutoDao produtoDao;

    @Autowired
    private ProdutoControl produtoControl = new ProdutoControl();

    private OrdemServicoProduto ordemServicoProduto = new OrdemServicoProduto();

    private static List<OrdemServicoProduto> listaOrdemServicoProduto = new ArrayList<>();

    private ProdutoConverter produtoConverter = new ProdutoConverter();

    private EntityConverter entityConverter = new EntityConverter();

    @PostConstruct
    public void init(){
        listar();
    }

    public void limpar(){
        this.ordemServicoProduto = new OrdemServicoProduto();
    }

    public void salvar(){
        String mensagem = "Ordem de Serviço - Produtos alterada com sucesso!";
        if (ordemServicoProduto.getId() == null) {
            mensagem = "Ordem de Serviço - Produtos salva com sucesso!";
        }
        try {
            setarQuantidadeNoEstoque(ordemServicoProduto, produtoControl);

            ordemServicoProduto.setPreco(ordemServicoProduto.getTotalProdutos());
            ordemServicoProdutoDao.save(ordemServicoProduto);

            ordemServicoProduto.getOrdemServico().getProdutos().add(ordemServicoProduto);
            ordemServicoProduto.getOrdemServico().setTotal(ordemServicoProduto.getOrdemServico().getTotal());
            ordemServicoDao.save(ordemServicoProduto.getOrdemServico());

            ordemServicoProduto = new OrdemServicoProduto();
            listar();
            Messages.addGlobalInfo(mensagem);
        } catch (Exception e){
            Messages.addGlobalError("Quantidade insuficiente no estoque");
        }

    }

    public void listar(){
        listaOrdemServicoProduto = ordemServicoProdutoDao.findAll();
    }

    public void excluir(Integer id){
        ordemServicoProdutoDao.deleteById(id);
        listar();
        Messages.addGlobalInfo("Ordem de Serviço - Produtos excluida com sucesso!");
    }

    public List<OrdemServico> completeOrdemServico(String query) {
        return ordemServicoDao.listarPorCliente("%" + query + "%");
    }

    public List<Produto> completeProduto(String query) {
        return produtoDao.listarPorNome("%" + query + "%");
    }

    public OrdemServicoProdutoDao getOrdemServicoProdutoDao() {
        return ordemServicoProdutoDao;
    }

    public void setOrdemServicoProdutoDao(OrdemServicoProdutoDao ordemServicoProdutoDao) {
        this.ordemServicoProdutoDao = ordemServicoProdutoDao;
    }

    public OrdemServicoProduto getOrdemServicoProduto() {
        return ordemServicoProduto;
    }

    public void setOrdemServicoProduto(OrdemServicoProduto ordemServicoProduto) {
        this.ordemServicoProduto = ordemServicoProduto;
        Messages.addGlobalInfo("Ordem de Serviço - Produtos alterada com sucesso");
    }

    public List<OrdemServicoProduto> getListaOrdemServicoProduto() {
        return listaOrdemServicoProduto;
    }

    public void setListaOrdemServicoProduto(List<OrdemServicoProduto> listaOrdemServicoProduto) {
        this.listaOrdemServicoProduto = listaOrdemServicoProduto;
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

    public static OrdemServicoProduto getOrdemServicoById(String value){
        return listaOrdemServicoProduto.stream().filter(e -> e.getId().equals(Integer.parseInt(value))).findAny().orElse(null);
    }

    public void setarQuantidadeNoEstoque(OrdemServicoProduto ordemServicoProduto, ProdutoControl produtoControl) throws Exception {
        for (Produto produto : produtoControl.getProdutos()) {
            if (produto.getId() == ordemServicoProduto.getProduto().getId()) {
                if (ordemServicoProduto.getQuantidade() > produto.getQuantidade()) {
                    throw new Exception();
                } else {
                    produto.setQuantidade(produto.getQuantidade() - ordemServicoProduto.getQuantidade());
                }
            }
        }
    }

}
