package br.com.helpthenext.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.helpthenext.enums.Causas;

@Table(name = "tb_evento")
@Entity
@NamedQueries({ @NamedQuery(name = "EventoEntity.findAll", query = "SELECT p FROM EventoEntity p") })
public class EventoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_evento")
	private Long id;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "nome_responsavel")
	private String nomeResponsavel;

	@Column(name = "banner", length = 10000000)
	private byte[] banner;

	@Column(name = "local")
	private String local;

	@Column(name = "email")
	private String email;

	@Column(name = "horario")
	private Date dataHora;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "causa")
	private Causas causa;

	@ManyToOne
	@JoinColumn(name = "id_ong")
	private ONGEntity ongEntity;

	@ManyToOne
	@JoinColumn(name = "id_voluntario")
	private VoluntarioEntity voluntarioEntity;

	@Column(name = "dt_cadastro")
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
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

	public Causas getCausa() {
		return causa;
	}

	public void setCausa(Causas causa) {
		this.causa = causa;
	}

}
