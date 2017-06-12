package br.com.helpthenext.repository.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.helpthenext.enums.Causas;

@Table(name = "tb_evento")
@Entity
@NamedQueries({
	 
	@NamedQuery(name = "EventoEntity.findAll",query= "SELECT p FROM EventoEntity p")
 
})
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

	@Column(name = "banner")
	private byte[] banner;

	@Column(name = "local")
	private String local;

	@Column(name = "email")
	private String email;
	
	@Column(name = "horario")
	private Date dataHora;

	// @Column(name = "causas")
	@Transient
	private List<Causas> causas;

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

	public List<Causas> getCausas() {
		return causas;
	}

	public void setCausas(List<Causas> causas) {
		this.causas = causas;
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

	
}
