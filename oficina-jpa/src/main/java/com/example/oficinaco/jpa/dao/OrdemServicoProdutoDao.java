package com.example.oficinaco.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oficinaco.jpa.model.OrdemServicoProduto;

@Repository
public interface OrdemServicoProdutoDao extends JpaRepository<OrdemServicoProduto, Integer> {
}
