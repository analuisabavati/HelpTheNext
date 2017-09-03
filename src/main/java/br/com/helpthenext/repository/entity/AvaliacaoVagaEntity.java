package br.com.helpthenext.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_avaliacao_vaga")
@Entity
@NamedQueries({
	@NamedQuery(name = "AvaliacaoVagaEntity.findVagasAvaliadas", query = "SELECT v.idVaga FROM AvaliacaoVagaEntity v where v.idVoluntario = :idVoluntario"),
	@NamedQuery(name = "AvaliacaoVagaEntity.findAll", query = "SELECT v FROM AvaliacaoVagaEntity v ORDER BY v.idVoluntario"),
	@NamedQuery(name = "AvaliacaoVagaEntity.findByVoluntarioVaga", query = "SELECT v FROM AvaliacaoVagaEntity v where v.idVoluntario = :idVoluntario and v.idVaga = :idVaga"),
	@NamedQuery(name = "AvaliacaoVagaEntity.findByIdVaga", query = "SELECT v FROM AvaliacaoVagaEntity v where v.idVaga = :idVaga"),
	@NamedQuery(name = "AvaliacaoVagaEntity.findByIdVoluntario", query = "SELECT v FROM AvaliacaoVagaEntity v where v.idVoluntario = :id") })
public class AvaliacaoVagaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_avaliacao_vaga")
	private Long id;

	@Column(name = "id_vaga")
	private Long idVaga;

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

	public Long getIdVaga() {
		return idVaga;
	}

	public void setIdVaga(Long idVaga) {
		this.idVaga = idVaga;
	}
	
}
