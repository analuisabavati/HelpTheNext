package br.com.helpthenext.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "tb_avaliacao_evento")
@Entity
@IdClass(AvaliacaoEventoPk.class)
public class AvaliacaoEventoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @ManyToOne
    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento")
	private EventoEntity evento;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_voluntario", referencedColumnName = "id_voluntario")
	private VoluntarioEntity voluntario;

	@Column(name = "avaliacao")
	private Integer avaliacao;

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
