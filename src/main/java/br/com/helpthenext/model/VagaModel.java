package br.com.helpthenext.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.helpthenext.enums.Causas;
import br.com.helpthenext.enums.DiasSemana;
import br.com.helpthenext.enums.Habilidades;
import br.com.helpthenext.enums.Periodos;
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
	private LocalDateTime dataCadastro;

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public String[] toStringArrayCausas(List<Causas> causas) {

		if (causas == null || causas.isEmpty()) {
			return null;
		}

		List<String> stringCausas = new ArrayList<>();
		for (Causas causa : causas) {
			stringCausas.add(causa.toString());
		}

		return stringCausas.toArray(new String[stringCausas.size()]);
	}

	public String[] toStringArrayHabilidades(List<Habilidades> list) {

		if (list == null || list.isEmpty()) {
			return null;
		}

		List<String> stringCausas = new ArrayList<>();
		for (Habilidades x : list) {
			stringCausas.add(x.toString());
		}

		return stringCausas.toArray(new String[stringCausas.size()]);
	}

	public String[] toStringArrayPeriodos(List<Periodos> list) {

		if (list == null || list.isEmpty()) {
			return null;
		}

		List<String> stringCausas = new ArrayList<>();
		for (Periodos x : list) {
			stringCausas.add(x.toString());
		}

		return stringCausas.toArray(new String[stringCausas.size()]);
	}

	public String[] toStringArrayDias(List<DiasSemana> list) {

		if (list == null || list.isEmpty()) {
			return null;
		}

		List<String> stringCausas = new ArrayList<>();
		for (DiasSemana x : list) {
			stringCausas.add(x.toString());
		}

		return stringCausas.toArray(new String[stringCausas.size()]);
	}

}
