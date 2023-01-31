package com.example.oficinaco.jpa.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import com.example.oficinaco.jpa.dao.MunicipioDao;
import com.example.oficinaco.jpa.model.Modelo;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.oficinaco.jpa.dao.PessoaDao;
import com.example.oficinaco.jpa.model.Municipio;
import com.example.oficinaco.jpa.model.Pessoa;
import com.example.oficinaco.jpa.util.converters.EntityConverter;
import com.example.oficinaco.jpa.util.validadores.Validadores;

@Component
@SessionScoped
public class PessoaControl {

    @Autowired
    private PessoaDao pessoaDao;

    private Pessoa pessoa = new Pessoa();

    private List<Pessoa> funcionarios = new ArrayList<>();
    private List<Pessoa> clientes = new ArrayList<>();

    @Autowired
    private MunicipioDao municipioDao;

    private EntityConverter entityConverter = new EntityConverter();

    @PostConstruct
    public void init(){
        listar();
    }

    public void limpar(){
        this.pessoa = new Pessoa();
    }

    public void salvar(){
        String mensagem = null;
        try{
            mensagem = pessoa.isFuncionario() ? "Funcionário alterado com sucesso!" : "Cliente alterado com sucesso!";
            if (pessoa.getId() == null) {
                if(!Validadores.isCPF(pessoa.getCpf())){
                    mensagem = "CPF inválido!";
                    throw new Exception();
                }
                mensagem = pessoa.isFuncionario() ? "Funcionário salvo com sucesso!" : "Cliente salvo com sucesso!";
            }
            pessoaDao.save(pessoa);
            pessoa = new Pessoa();
            listar();
            Messages.addGlobalInfo(mensagem);
        } catch (Exception e){
            Messages.addGlobalError(mensagem);
        }
    }

    public void listar(){
        funcionarios = pessoaDao.listarPessoasIsFuncionario(true);
        clientes = pessoaDao.listarPessoasIsFuncionario(false);
    }

    public void excluir(Integer id){
        pessoaDao.deleteById(id);
        listar();
        Messages.addGlobalInfo(pessoa.isFuncionario() ? "Funcionário excluido com sucesso!" : "Cliente excluido com sucesso!");
    }

    public List<Municipio> completeMunicipio(String query) {
        return municipioDao.listarPorNome("%" + query + "%");
    }

    public PessoaDao getPessoaDao() {
        return pessoaDao;
    }

    public void setPessoaDao(PessoaDao pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        Messages.addGlobalInfo(pessoa.isFuncionario() ? "Funcionário alterado com sucesso!" : "Cliente alterado com sucesso!");
    }

    public List<Pessoa> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Pessoa> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Pessoa> getClientes() {
        return clientes;
    }

    public void setClientes(List<Pessoa> clientes) {
        this.clientes = clientes;
    }

    public EntityConverter getEntityConverter() {
        return entityConverter;
    }

    public void setEntityConverter(EntityConverter entityConverter) {
        this.entityConverter = entityConverter;
    }
}
