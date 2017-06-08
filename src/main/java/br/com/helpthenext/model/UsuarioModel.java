package br.com.helpthenext.model;

import java.io.Serializable;

import br.com.helpthenext.enums.TipoUsuario;

public class UsuarioModel implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	private String codigo;
	private String usuario;
	private String senha;
	private TipoUsuario tipoUsuario;
 
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
 
}