package br.com.helpthenext.repository.entity;

import java.io.Serializable;
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
import br.com.helpthenext.enums.Habilidades;

@Table(name = "tb_vaga")
@Entity
@NamedQueries({
	 
	@NamedQuery(name = "VagaEntity.findAll",query= "SELECT p FROM VagaEntity p")
 
})
public class VagaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_vaga")
	private String id;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "nome_responsavel")
	private String nomeResponsavel;

	@Column(name = "banner")
	private byte[] banner;
	
	@Column(name = "email")
	private String email;

//	@Column(name = "causas")
	@Transient
	private List<Causas> causas;

	//@Column(name = "habilidades")
	@Transient
	private List<Habilidades> habilidades;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
