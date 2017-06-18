package br.com.helpthenext.model;

import java.time.LocalDateTime;
import java.util.Date;

import br.com.helpthenext.repository.entity.UsuarioEntity;

public class VoluntarioModel {

	private Long id;
	private String nome;
	private String sobrenome;
	private String sexo;
	private String cpf;
	private String rg;
	private LocalDateTime dataCadastro;
	private Date dataNascimento;
	private String email;
	private String telefone;
	private String rua;
	private Long numero;
	private String complemento;
	private Long cep;
	private String cidade;
	private String estado;
	private String pais;
	private UsuarioEntity usuarioEntity = new UsuarioEntity();
	private byte[] fotoPerfil;
	private String[] habilidades;
	private String[] causas;
	private String[] disponibilidadeDias;
	private String[] disponibilidadePeriodos;

	public String[] getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(String[] habilidades) {
		this.habilidades = habilidades;
	}

	public String[] getCausas() {
		return causas;
	}

	public void setCausas(String[] causas) {
		this.causas = causas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public byte[] getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(byte[] bs) {
		this.fotoPerfil = bs;
	}

	public String[] getDisponibilidadeDias() {
		return disponibilidadeDias;
	}

	public void setDisponibilidadeDias(String[] disponibilidadeDias) {
		this.disponibilidadeDias = disponibilidadeDias;
	}

	public String[] getDisponibilidadePeriodos() {
		return disponibilidadePeriodos;
	}

	public void setDisponibilidadePeriodos(String[] disponibilidadePeriodos) {
		this.disponibilidadePeriodos = disponibilidadePeriodos;
	}
}