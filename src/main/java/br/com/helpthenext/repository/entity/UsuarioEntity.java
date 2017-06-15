package br.com.helpthenext.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.helpthenext.enums.TipoUsuario;

@Table(name="tb_usuario")
@Entity	
@NamedQueries({
	@NamedQuery(name = "UsuarioEntity.findUser", query= "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.senha = :senha"),
	@NamedQuery(name = "UsuarioEntity.findUserCadastro", query = "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario")
})

public class UsuarioEntity implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue
	@Column(name="id_usuario")
	private Long id;
 
	@Column(name="ds_login")
	private String usuario;
 
	@Column(name="ds_senha")
	private String senha;
	
	@Column(name="tp_usuario")
	private TipoUsuario tipoUsuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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