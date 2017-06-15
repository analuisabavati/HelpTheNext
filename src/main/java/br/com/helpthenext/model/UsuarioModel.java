package br.com.helpthenext.model;

import java.io.Serializable;

import br.com.helpthenext.enums.TipoUsuario;

public class UsuarioModel implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	private Long codigo;
	private String usuario;
	private String senha;
	private TipoUsuario tipoUsuario;
 
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
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