package com.example.oficinaco.jpa.control;

import com.example.oficinaco.jpa.dao.UsuarioDao;
import com.example.oficinaco.jpa.model.Modelo;
import com.example.oficinaco.jpa.model.Usuario;
import com.example.oficinaco.jpa.util.criptografia.Criptografia;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@SessionScoped
public class UsuarioControl {
	
	@Autowired
	private UsuarioDao usuarioDao;

	private Usuario usuario = new Usuario();
	
	private List<Usuario> usuarios = new ArrayList<>();

	@PostConstruct
	public void init(){
		listar();
	}

	public void limpar(){
		this.usuario = new Usuario();
	}
	
	public void salvar() {
		String mensagem = null;
		try {
			mensagem = "Usuário alterado com sucesso!";
			if (usuario.getId() == null) {
				mensagem = "Usuário salvo com sucesso!";
				List<Usuario> usuarios = usuarioDao.listarPorEmail(usuario.getEmail());
				if (!usuarios.isEmpty()) {
					mensagem = "Já existe um usuário com esse email!";
					throw new Exception();
				}
			}
			usuario.setSenha(Criptografia.criptografar(usuario.getSenha()));
			usuarioDao.save(usuario);
			usuario = new Usuario();
			listar();
			Messages.addGlobalInfo(mensagem);
		} catch (Exception e) {
			Messages.addGlobalError(mensagem);
		}
	}

	public void listar() {
		usuarios = usuarioDao.findAll();
	}
	
	public void excluir(Integer id) {
		usuarioDao.deleteById(id);
		listar();
		Messages.addGlobalInfo("Usuário excluido com sucesso!");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		Messages.addGlobalInfo("Usuário alterado com sucesso");
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String validaUsuario(Usuario usuario) {

		Usuario usuarioEncontrado = usuarioDao.consultarPorEmail(usuario.getEmail());

		String senhaCriptografada = Criptografia.criptografar(usuario.getSenha());

		if (!Objects.isNull(usuarioEncontrado) && usuarioEncontrado.getSenha().equals(senhaCriptografada)) {

			return "/cliente.xhtml";

		}

		else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", "Erro no Login!"));

			return null;

		}

	}
	
}














