package com.example.oficinaco.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.oficinaco.jpa.model.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer>{

    @Query(nativeQuery = true, value = "select * from usuario u where lower(u.email) like lower(:email)")
    Usuario consultarPorEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = "select * from usuario u where lower(u.email) like lower(:email)")
    List<Usuario> listarPorEmail(@Param("email") String email);

}
