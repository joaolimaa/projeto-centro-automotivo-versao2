package com.example.oficinaco.jpa.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.oficinaco.jpa.dao.MarcaDao;
import com.example.oficinaco.jpa.model.Marca;

@Component
@SessionScoped
public class MarcaControl implements IControl {

    @Autowired
    private MarcaDao marcaDao;

    private Marca marca = new Marca();

    private List<Marca> marcas = new ArrayList<>();

    @PostConstruct
    public void init() {
        listar();
    }

    public void salvar() {
        String mensagem = null;
        try {
            mensagem = "Marca alterada com sucesso!";
            if (marca.getId() == null) {
                mensagem = "Marca salva com sucesso!";
                List<Marca> marcas = marcaDao.listarPorNome(marca.getNome());
                if (!marcas.isEmpty()) {
                    mensagem = "Já existe uma marca com esse nome!";
                    throw new Exception();
                }
            }
            marcaDao.save(marca);
            marca = new Marca();
            listar();
            Messages.addGlobalInfo(mensagem);
        } catch (Exception e) {
            Messages.addGlobalError(mensagem);
        }
    }

    public void listar() {
        marcas = marcaDao.findAll();
    }

    public void limpar(){
        this.marca = new Marca();
    }

    public void excluir(Integer id) {
        marcaDao.deleteById(id);
        listar();
        Messages.addGlobalInfo("Marca excluida com sucesso!");
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
        Messages.addGlobalInfo("Marca alterada com sucesso");
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }
}