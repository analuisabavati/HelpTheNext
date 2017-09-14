package br.com.helpthenext.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.helpthenext.enums.Causas;

@Entity
@Table(name = "tb_ong")
@NamedQueries({
	@NamedQuery(name = "ONGEntity.findByUsuario", query= "SELECT v FROM ONGEntity v where v.usuarioEntity = :usuario")
})
public class ONGEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_ong")
	private Long id;

	@Column(name = "nome_ong")
	private String nomeONG;

	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String  telefone;
	
	@Column(name = "rua")
	private String  rua;
	
	@Column(name = "numero")
	private Long  numero;
	
	@Column(name = "complemento")
	private String  complemento;
	
	@Column(name = "cep")
	private String  cep;
	
	@Column(name = "cidade")
	private String  cidade;
 
	@Column(name = "estado")
	private String  estado;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuarioEntity;

	@Column(name = "ft_logo", length=10000000)
	private byte[] foto;

	@Column(name = "nome_responsavel")
	private String nomeResponsavel;

	@Column(name = "descricao")
	private String descricao;

	@ElementCollection(targetClass=Causas.class)
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name="ong_causas")
    @Column(name="causas") 
	private List<Causas> causas;

	@Column(name = "website")
	private String website;

	@Column(name = "facebook")
	private String facebook;
	
	@OneToMany(mappedBy = "ongEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EventoEntity> eventos;
	
	@OneToMany(mappedBy = "ongEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VagaEntity> vagas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public String getNomeONG() {
		return nomeONG;
	}

	public void setNomeONG(String nomeONG) {
		this.nomeONG = nomeONG;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Causas> getCausas() {
		return causas;
	}

	public void setCausas(List<Causas> causas) {
		this.causas = causas;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public List<EventoEntity> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoEntity> eventos) {
		this.eventos = eventos;
	}

	public List<VagaEntity> getVagas() {
		return vagas;
	}

	public void setVagas(List<VagaEntity> vagas) {
		this.vagas = vagas;
	}

	
}