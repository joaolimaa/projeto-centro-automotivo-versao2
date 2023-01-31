package com.example.oficinaco.jpa.control;

public interface IControl {

    void init();

    void salvar();

    void listar();

    void limpar();

    void excluir(Integer id);
}
