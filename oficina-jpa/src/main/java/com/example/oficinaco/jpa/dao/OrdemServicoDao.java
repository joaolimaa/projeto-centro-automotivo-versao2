package com.example.oficinaco.jpa.dao;

import com.example.oficinaco.jpa.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.oficinaco.jpa.model.OrdemServico;

import java.util.List;

@Repository
public interface OrdemServicoDao extends JpaRepository<OrdemServico, Integer> {

    @Query(nativeQuery = true, value = "select * from ordem_servico os inner join pessoa p on p.id=os.cliente_id where lower(p.nome) like lower(:nome)")
    List<OrdemServico> listarPorCliente(@Param("nome") String nome);

}
