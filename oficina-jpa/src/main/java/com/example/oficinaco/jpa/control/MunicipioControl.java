package com.example.oficinaco.jpa.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import com.example.oficinaco.jpa.model.Modelo;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.oficinaco.jpa.dao.MunicipioDao;
import com.example.oficinaco.jpa.model.Municipio;
import com.example.oficinaco.jpa.util.converters.EntityConverter;

@Component
@SessionScoped
public class MunicipioControl {

    @Autowired
    private MunicipioDao municipioDao;

    private Municipio municipio = new Municipio();

    private List<Municipio> municipios = new ArrayList<>();

    private EntityConverter entityConverter;

    @PostConstruct
    public void init(){
        listar();
    }

    public void limpar(){
        this.municipio = new Municipio();
    }

    public void salvar() {
        String mensagem = null;
        try {
            mensagem = "Município alterado com sucesso!";
            if (municipio.getId() == null) {
                mensagem = "Município salvo com sucesso!";
                List<Municipio> municipios = municipioDao.listarPorNome(municipio.getNome());
                if (!municipios.isEmpty()) {
                    mensagem = "Já existe um município com esse nome!";
                    throw new Exception();
                }
            }
            municipioDao.save(municipio);
            municipio = new Municipio();
            listar();
            Messages.addGlobalInfo(mensagem);
        } catch (Exception e) {
            Messages.addGlobalError(mensagem);
        }
    }

    public void listar(){
        municipios = municipioDao.findAll();
    }

    public void excluir(Integer id){
        municipioDao.deleteById(id);
        listar();
        Messages.addGlobalInfo("Município excluido com sucesso!");
    }

    public MunicipioDao getMunicipioDao() {
        return municipioDao;
    }

    public void setMunicipioDao(MunicipioDao municipioDao) {
        this.municipioDao = municipioDao;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
        Messages.addGlobalInfo("Município alterado com sucesso");
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public EntityConverter getEntityConverter() {
        return entityConverter;
    }

    public void setEntityConverter(EntityConverter entityConverter) {
        this.entityConverter = entityConverter;
    }
}
