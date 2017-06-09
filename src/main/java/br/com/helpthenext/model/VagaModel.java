package br.com.helpthenext.model;

import java.util.List;

import br.com.helpthenext.enums.Causas;
import br.com.helpthenext.enums.Habilidades;

public class VagaModel {

	private String titulo;
	private String descricao;
	private String nomeResponsavel;
	private String email;
	private byte[] banner;
	private List<Causas> causas;
	private List<Habilidades> habilidades;

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

	public List<Causas> getCausas() {
		return causas;
	}

	public void setCausas(List<Causas> causas) {
		this.causas = causas;
	}

	public List<Habilidades> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidades> habilidades) {
		this.habilidades = habilidades;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
