package br.com.helpthenext.model;

import java.util.Date;

import org.primefaces.model.StreamedContent;

import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.repository.entity.VoluntarioEntity;

public class EventoModel {

	private Long id;
	private String titulo;
	private String descricao;
	private String nomeResponsavel;
	private byte[] banner;
	private String local;
	private String email;
	private Date dataHora;
	private String[] causas;
	private ONGEntity ongEntity;
	private VoluntarioEntity voluntarioEntity;

	public ONGEntity getOngEntity() {
		return ongEntity;
	}

	public void setOngEntity(ONGEntity ongEntity) {
		this.ongEntity = ongEntity;
	}

	public VoluntarioEntity getVoluntarioEntity() {
		return voluntarioEntity;
	}

	public void setVoluntarioEntity(VoluntarioEntity voluntarioEntity) {
		this.voluntarioEntity = voluntarioEntity;
	}

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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getCausas() {
		return causas;
	}

	public void setCausas(String[] causas) {
		this.causas = causas;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	
}
