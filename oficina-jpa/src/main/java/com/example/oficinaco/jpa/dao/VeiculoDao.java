package com.example.oficinaco.jpa.dao;

import com.example.oficinaco.jpa.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.oficinaco.jpa.model.Veiculo;

import java.util.List;

@Repository
public interface VeiculoDao extends JpaRepository<Veiculo, Integer>{
	
	@Query(nativeQuery = true, value = "select * from veiculo where lower(placa) = lower(:placa)")
	Veiculo consultarPorPlaca(@Param("placa") String placa);

	@Query(nativeQuery = true, value = "select * from veiculo where lower(placa) = lower(:placa)")
	List<Veiculo> listarPorPlaca(@Param("placa") String placa);

	@Query(nativeQuery = true, value = "select * from veiculo v inner join modelo m on m.id=v.modelo_id where lower(m.nome) like lower(:nome)")
	List<Veiculo> listarPorModelo(@Param("nome") String nome);

}
