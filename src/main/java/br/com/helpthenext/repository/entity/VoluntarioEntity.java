package br.com.helpthenext.repository.entity;

import java.time.LocalDateTime;
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
@Table(name="tb_pessoa")
public class VoluntarioEntity {
 
	@Id
	@GeneratedValue
	@Column(name = "id_pessoa")
	private Integer codigo;
 
	@Column(name = "nome_pessoa")
	private String  nome;
 
	@Column(name = "sexo")
	private String  sexo;
 
	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;
 
	@Column(name = "email")
	private String  email;
 
	@Column(name = "endereco")
	private String  endereco;
 
	@Column(name = "origemCadastro")
	private String  origemCadastro;
 
	@OneToOne
	@JoinColumn(name="id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;
	
	@Column(name = "ft_perfil")
	private byte[] foto;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Transient
	private List<Causas> causas;

	@Transient
	private List<Causas> habilidades;

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getOrigemCadastro() {
		return origemCadastro;
	}
	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}
	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}
	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}
 
}