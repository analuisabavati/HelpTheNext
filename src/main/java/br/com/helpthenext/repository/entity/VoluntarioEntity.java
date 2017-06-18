package br.com.helpthenext.repository.entity;

import java.time.LocalDateTime;
import java.util.Date;
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
import br.com.helpthenext.enums.DiasSemana;
import br.com.helpthenext.enums.Habilidades;
import br.com.helpthenext.enums.Periodos;

@Entity
@Table(name = "tb_voluntario")
@NamedQueries({
	
	@NamedQuery(name = "VoluntarioEntity.findByUsuario", query= "SELECT v FROM VoluntarioEntity v where v.usuarioEntity = :usuario")
})
public class VoluntarioEntity {

	@Id
	@GeneratedValue
	@Column(name = "id_voluntario")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sobrenome")
	private String sobrenome;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "rg")
	private String rg;

	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name = "dt_nascimento")
	private Date dataNascimento;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "rua")
	private String rua;

	@Column(name = "numero")
	private Long numero;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "cep")
	private String cep;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "estado")
	private String estado;

	@Column(name = "pais")
	private String pais;

	@OneToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuarioEntity;

	@Column(name = "ft_perfil", length=100000)
	private byte[] foto;

	@ElementCollection(targetClass = Causas.class)
	@Enumerated(EnumType.ORDINAL)
	@CollectionTable(name = "voluntario_causas")
	@Column(name = "causas")
	private List<Causas> causas;

	@ElementCollection(targetClass = Habilidades.class)
	@Enumerated(EnumType.ORDINAL)
	@CollectionTable(name = "voluntario_habilidades")
	@Column(name = "habilidades")
	private List<Habilidades> habilidades;

	@ElementCollection(targetClass = DiasSemana.class)
	@Enumerated(EnumType.ORDINAL)
	@CollectionTable(name = "voluntario_dias_disponiveis")
	@Column(name = "dias_disponiveis")
	private List<DiasSemana> diasDisponiveis;

	@ElementCollection(targetClass = Periodos.class)
	@Enumerated(EnumType.ORDINAL)
	@CollectionTable(name = "voluntario_periodos_disponiveis")
	@Column(name = "periodos_disponiveis")
	private List<Periodos> periodosDisponiveis;

	@OneToMany(mappedBy = "voluntarioEntity", cascade = CascadeType.ALL)
	private List<DoacaoEntity> doacoes;
	
	@OneToMany(mappedBy = "voluntarioEntity", cascade = CascadeType.ALL)
	private List<EventoEntity> eventos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public List<Causas> getCausas() {
		return causas;
	}

	public void setCausas(List<Causas> causas) {
		this.causas = causas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Habilidades> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidades> habilidades) {
		this.habilidades = habilidades;
	}

	public List<DiasSemana> getDiasDisponiveis() {
		return diasDisponiveis;
	}

	public void setDiasDisponiveis(List<DiasSemana> diasDisponiveis) {
		this.diasDisponiveis = diasDisponiveis;
	}

	public List<Periodos> getPeriodosDisponiveis() {
		return periodosDisponiveis;
	}

	public void setPeriodosDisponiveis(List<Periodos> periodosDisponiveis) {
		this.periodosDisponiveis = periodosDisponiveis;
	}

	public List<DoacaoEntity> getDoacoes() {
		return doacoes;
	}

	public void setDoacoes(List<DoacaoEntity> doacoes) {
		this.doacoes = doacoes;
	}

	public List<EventoEntity> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoEntity> eventos) {
		this.eventos = eventos;
	}

	
}