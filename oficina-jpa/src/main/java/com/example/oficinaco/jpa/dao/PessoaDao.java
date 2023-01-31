package com.example.oficinaco.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.oficinaco.jpa.model.Pessoa;

@Repository
public interface PessoaDao extends JpaRepository<Pessoa, Integer>{

	@Query(nativeQuery = true, value = "select * from pessoa p where lower(p.nome) like lower(:nome)")
	List<Pessoa> listarPorNome(@Param("nome") String nome);
	
	@Query(nativeQuery = true, value = "select * from pessoa p where p.funcionario = true and lower(p.nome) like lower(:nome)")
	List<Pessoa> listarFuncionarioPorNome(@Param("nome") String nome);

	@Query(nativeQuery = true, value = "select * from pessoa p where p.funcionario = :isFuncionario")
	List<Pessoa> listarPessoasIsFuncionario(@Param("isFuncionario") boolean isFuncionario);

	@Query(nativeQuery = true, value = "select * from pessoa p where p.funcionario = false and lower(p.nome) like lower(:nome)")
	List<Pessoa> listarClientePorNome(@Param("nome") String nome);

}
