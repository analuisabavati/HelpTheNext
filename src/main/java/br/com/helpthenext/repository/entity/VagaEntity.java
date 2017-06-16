package br.com.helpthenext.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import br.com.helpthenext.enums.DiasSemana;
import br.com.helpthenext.enums.Habilidades;
import br.com.helpthenext.enums.Periodos;

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
	private Long id;

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

	@ElementCollection(targetClass=Causas.class)
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name="vaga_causas")
    @Column(name="causas") 
	private List<Causas> causas;

	@ElementCollection(targetClass=Habilidades.class)
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name="vaga_habilidades")
    @Column(name="habilidades") 
	private List<Habilidades> habilidades;
	
	@ElementCollection(targetClass = DiasSemana.class)
	@Enumerated(EnumType.ORDINAL)
	@CollectionTable(name = "vaga_dias")
	@Column(name = "dias")
	private List<DiasSemana> dias;

	@ElementCollection(targetClass = Periodos.class)
	@Enumerated(EnumType.ORDINAL)
	@CollectionTable(name = "vaga_periodos")
	@Column(name = "periodos")
	private List<Periodos> periodos;
	
	@ManyToOne
	@JoinColumn(name="id_ong")
	private ONGEntity ongEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ONGEntity getOngEntity() {
		return ongEntity;
	}

	public void setOngEntity(ONGEntity ongEntity) {
		this.ongEntity = ongEntity;
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

	public List<DiasSemana> getDias() {
		return dias;
	}

	public void setDias(List<DiasSemana> dias) {
		this.dias = dias;
	}

	public List<Periodos> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodos> periodos) {
		this.periodos = periodos;
	}
		
}
