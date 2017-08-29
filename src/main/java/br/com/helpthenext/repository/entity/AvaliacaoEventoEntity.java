package br.com.helpthenext.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "tb_avaliacao_evento")
@Entity
public class AvaliacaoEventoEntity  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_avaliacao_evento")
	private Long id;

	@ManyToOne
	@Column(name = "id_evento")
	private EventoEntity evento;

	@ManyToOne
	@Column(name = "id_voluntario")
	private VoluntarioEntity voluntario;
	
	@Column(name = "id_avaliacao_evento")
	private Integer avaliacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventoEntity getEvento() {
		return evento;
	}

	public void setEvento(EventoEntity evento) {
		this.evento = evento;
	}

	public VoluntarioEntity getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(VoluntarioEntity voluntario) {
		this.voluntario = voluntario;
	}

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
