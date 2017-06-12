package br.com.helpthenext.model;

import br.com.helpthenext.repository.entity.VoluntarioEntity;

public class DoacaoModel {

	private Long id;
	private String titulo;
	private String descricao;
	private byte[] foto;
	private VoluntarioEntity voluntarioEntity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public VoluntarioEntity getVoluntarioEntity() {
		return voluntarioEntity;
	}
	public void setVoluntarioEntity(VoluntarioEntity voluntarioEntity) {
		this.voluntarioEntity = voluntarioEntity;
	}
	
}