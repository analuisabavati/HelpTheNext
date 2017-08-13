package br.com.helpthenext.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.helpthenext.enums.Causas;
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
	private LocalDateTime dataCadastro;
	private String causasString;
	private Date dataCadastroDate;
	
	public Date getDataCadastroDate() {
		return dataCadastroDate;
	}

	public void setDataCadastroDate(Date dataCadastroDate) {
		this.dataCadastroDate = dataCadastroDate;
	}


	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

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
	
	public String[] toStringArray(List<Causas> causas) {
		
		if (causas == null || causas.isEmpty()) {
			return null;
		}
		
		List<String> stringCausas = new ArrayList<>();
		for(Causas causa: causas) {
			stringCausas.add(causa.toString());
		}
		
		return stringCausas.toArray(new String[stringCausas.size()]);
	}

	public String getCausasString() {
		return causasString;
	}

	public void setCausasString(String causasString) {
		this.causasString = causasString;
	}

	
}
