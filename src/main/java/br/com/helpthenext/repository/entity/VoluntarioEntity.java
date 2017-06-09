package br.com.helpthenext.repository.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.helpthenext.enums.Causas;
  
@Entity
@Table(name="tb_voluntario")
public class VoluntarioEntity {
 
	@Id
	@GeneratedValue
	@Column(name = "id_voluntario")
	private Integer id;
 
	@Column(name = "nome")
	private String  nome;
	
	@Column(name = "sobrenome")
	private String  sobrenome;
 
	@Column(name = "sexo")
	private String  sexo;
	
	@Column(name = "cpf")
	private String  cpf;
	
	@Column(name = "rg")
	private String  rg;
 
	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;
	
	@Column(name = "dt_nascimento")
	private Date dataNascimento;
 
	@Column(name = "email")
	private String  email;
 
	@Column(name = "telefone")
	private String  telefone;
	
	@Column(name = "rua")
	private String  rua;
	
	@Column(name = "numero")
	private Long  numero;
	
	@Column(name = "complemento")
	private String  complemento;
	
	@Column(name = "cep")
	private Long  cep;
	
	@Column(name = "cidade")
	private String  cidade;
 
	@Column(name = "estado")
	private String  estado;
	
	@Column(name = "pais")
	private String  pais;
	
	@OneToOne
	@JoinColumn(name="id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;
	
	@Column(name = "ft_perfil")
	private byte[] foto;
	
	@Transient
	private List<Causas> causas;

	@Transient
	private List<Causas> habilidades;

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
	public Long getCep() {
		return cep;
	}
	public void setCep(Long cep) {
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
	public List<Causas> getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(List<Causas> habilidades) {
		this.habilidades = habilidades;
	}
	
	
 
}