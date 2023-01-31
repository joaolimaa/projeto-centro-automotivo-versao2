package com.example.oficinaco.jpa.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import com.example.oficinaco.jpa.dao.OrdemServicoDao;
import com.example.oficinaco.jpa.model.OrdemServico;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.oficinaco.jpa.dao.OrdemServicoServicoDao;
import com.example.oficinaco.jpa.model.OrdemServicoServico;
import com.example.oficinaco.jpa.util.converters.EntityConverter;
import com.example.oficinaco.jpa.util.converters.OrdemServicoConverter;
import com.example.oficinaco.jpa.util.converters.ServicoConverter;

@Component
@SessionScoped
public class OrdemServicoServicoControl {

    @Autowired
    private OrdemServicoServicoDao ordemServicoServicoDao;

    @Autowired
    private OrdemServicoDao ordemServicoDao;

    @Autowired
    private OrdemServicoControl ordemServicoControl;

    private OrdemServicoServico ordemServicoServico = new OrdemServicoServico();

    private static List<OrdemServicoServico> listaOrdemServicoServico = new ArrayList<>();

    private ServicoConverter servicoConverter = new ServicoConverter();

    private EntityConverter entityConverter = new EntityConverter();

    private OrdemServicoConverter ordemServicoConverter = new OrdemServicoConverter();

    @PostConstruct
    public void init(){
        listar();
    }

    public void limpar(){
        this.ordemServicoServico = new OrdemServicoServico();
    }

    public void salvar(){
        String mensagem = "Ordem serviço - Serviços alterada com sucesso!";
        if (ordemServicoServico.getId() == null) {
            mensagem = "Ordem serviço - Serviços salva com sucesso!";
        }

        ordemServicoServico.setPreco(ordemServicoServico.getTotalServicos());
        ordemServicoServico.setQuantidade(ordemServicoServico.getServicos().size());
        ordemServicoServicoDao.save(ordemServicoServico);

        ordemServicoServico.getOrdemServico().setServicos(ordemServicoServico);
        ordemServicoServico.getOrdemServico().setTotal(ordemServicoServico.getOrdemServico().getTotal());
        ordemServicoControl.salvarEntidade(ordemServicoServico.getOrdemServico());

        ordemServicoServico = new OrdemServicoServico();
        listar();
        Messages.addGlobalInfo(mensagem);
    }

    public void listar(){
        listaOrdemServicoServico = ordemServicoServicoDao.findAll();
    }

    public void excluir(Integer id){
        ordemServicoServicoDao.deleteById(id);
        listar();
        Messages.addGlobalInfo("Ordem serviço - Serviços excluida com sucesso!");
    }

    public List<OrdemServico> completeOrdemServico(String query) {
        return ordemServicoDao.listarPorCliente("%" + query + "%");
    }

    public OrdemServicoServicoDao getOrdemServicoServicoDao() {
        return ordemServicoServicoDao;
    }

    public void setOrdemServicoServicoDao(OrdemServicoServicoDao ordemServicoServicoDao) {
        this.ordemServicoServicoDao = ordemServicoServicoDao;
    }

    public OrdemServicoServico getOrdemServicoServico() {
        return ordemServicoServico;
    }

    public void setOrdemServicoServico(OrdemServicoServico ordemServicoServico) {
        this.ordemServicoServico = ordemServicoServico;
        Messages.addGlobalInfo("Ordem serviço - Serviços alterada com sucesso");
    }

    public List<OrdemServicoServico> getListaOrdemServicoServico() {
        return listaOrdemServicoServico;
    }

    public void setListaOrdemServicoServico(List<OrdemServicoServico> listaOrdemServicoServico) {
        this.listaOrdemServicoServico = listaOrdemServicoServico;
    }

    public ServicoConverter getServicoConverter() {
        return servicoConverter;
    }

    public void setServicoConverter(ServicoConverter servicoConverter) {
        this.servicoConverter = servicoConverter;
    }

    public EntityConverter getEntityConverter() {
        return entityConverter;
    }

    public void setEntityConverter(EntityConverter entityConverter) {
        this.entityConverter = entityConverter;
    }

    public OrdemServicoConverter getOrdemServicoConverter() {
        return ordemServicoConverter;
    }

    public void setOrdemServicoConverter(OrdemServicoConverter ordemServicoConverter) {
        this.ordemServicoConverter = ordemServicoConverter;
    }
}
