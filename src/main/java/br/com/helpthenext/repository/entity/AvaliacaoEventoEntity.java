package br.com.helpthenext.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_avaliacao_evento")
@Entity
@NamedQueries({
		@NamedQuery(name = "AvaliacaoEventoEntity.findAll", query = "SELECT v FROM AvaliacaoEventoEntity v"),
		@NamedQuery(name = "AvaliacaoEventoEntity.findByVoluntarioEvento", query = "SELECT v FROM AvaliacaoEventoEntity v where v.idVoluntario = :idVoluntario and v.idEvento = :idEvento"),
		@NamedQuery(name = "AvaliacaoEventoEntity.findByIdVoluntario", query = "SELECT v FROM AvaliacaoEventoEntity v where v.idEvento = :id"),
		@NamedQuery(name = "AvaliacaoEventoEntity.findByIdEvento", query = "SELECT v FROM AvaliacaoEventoEntity v where v.idVoluntario = :id") })
public class AvaliacaoEventoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_avaliacao_evento")
	private Long id;

	@Column(name = "id_evento")
	private Long idEvento;

	@Column(name = "id_voluntario")
	private Long idVoluntario;

	@Column(name = "avaliacao")
	private Integer avaliacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public Long getIdVoluntario() {
		return idVoluntario;
	}

	public void setIdVoluntario(Long idVoluntario) {
		this.idVoluntario = idVoluntario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}

}
