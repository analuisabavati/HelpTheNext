package br.com.helpthenext.model;

import br.com.helpthenext.repository.entity.ONGEntity;

public class VagaModel {

	private Long id;
	private String titulo;
	private String descricao;
	private String nomeResponsavel;
	private String email;
	private byte[] banner;
	private String[] causas;
	private String[] habilidades;
	private ONGEntity ongEntity;
	private String[] dias;
	private String[] periodos;

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

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public byte[] getBanner() {
		return banner;
	}

	public void setBanner(byte[] banner) {
		this.banner = banner;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ONGEntity getOngEntity() {
		return ongEntity;
	}

	public void setOngEntity(ONGEntity ongEntity) {
		this.ongEntity = ongEntity;
	}

	public String[] getCausas() {
		return causas;
	}

	public void setCausas(String[] causas) {
		this.causas = causas;
	}

	public String[] getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(String[] habilidades) {
		this.habilidades = habilidades;
	}

	public void setDias(String[] dias) {
		this.dias = dias;
	}

	public void setPeriodos(String[] periodos) {
		this.periodos = periodos;
	}

	public String[] getDias() {
		return dias;
	}

	public String[] getPeriodos() {
		return periodos;
	}
	
}
