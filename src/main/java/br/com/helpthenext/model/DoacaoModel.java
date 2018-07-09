package br.com.helpthenext.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.helpthenext.enums.DiasSemana;
import br.com.helpthenext.enums.Periodos;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.util.DadosUtil;

public class DoacaoModel {

	private Long id;
	private String titulo;
	private String descricao;
	private byte[] foto;
	private VoluntarioEntity voluntarioEntity;
	private String[] dias;
	private String[] periodos;
	private LocalDateTime dataCadastro;
	
	private String diasString;
	private String periodoString;
	
	public String getDiasString() {
		return diasString;
	}

	public void setDiasString(String diasString) {
		this.diasString = DadosUtil.formataDias(diasString);
	}

	public String getPeriodoString() {
		return periodoString;
	}

	public void setPeriodoString(String periodoString) {
		this.periodoString = DadosUtil.formataPeriodos(periodoString);
	}


	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public String[] getDias() {
		return dias;
	}
	public void setDias(String[] dias) {
		this.dias = dias;
	}
	public String[] getPeriodos() {
		return periodos;
	}
	public void setPeriodos(String[] periodos) {
		this.periodos = periodos;
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
	
	public String[] toStringArrayPeriodos(List<Periodos> list) {

		if (list == null || list.isEmpty()) {
			return null;
		}

		List<String> stringCausas = new ArrayList<>();
		for (Periodos x : list) {
			Integer ordinal = x.ordinal();
			stringCausas.add(ordinal.toString());
		}

		return stringCausas.toArray(new String[stringCausas.size()]);
	}

	public String[] toStringArrayDias(List<DiasSemana> list) {

		if (list == null || list.isEmpty()) {
			return null;
		}

		List<String> stringCausas = new ArrayList<>();
		for (DiasSemana x : list) {
			Integer ordinal = x.ordinal();
			stringCausas.add(ordinal.toString());
		}

		return stringCausas.toArray(new String[stringCausas.size()]);
	}

	
}
