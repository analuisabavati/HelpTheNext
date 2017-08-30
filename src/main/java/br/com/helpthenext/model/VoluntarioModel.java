package br.com.helpthenext.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.helpthenext.enums.Causas;
import br.com.helpthenext.enums.DiasSemana;
import br.com.helpthenext.enums.Habilidades;
import br.com.helpthenext.enums.Periodos;
import br.com.helpthenext.repository.entity.AvaliacaoEventoEntity;
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
	private String cep;
	private String cidade;
	private String estado;
	private String pais;
	private UsuarioEntity usuarioEntity = new UsuarioEntity();
	private byte[] fotoPerfil;
	private String[] habilidades;
	private String[] causas;
	private String[] disponibilidadeDias;
	private String[] disponibilidadePeriodos;
	private String trabalhoDistancia;

	private String senha;

	private Integer pontos;

	private String habilidadesString;
	private String causasString;
	private String disponibilidadeDiasString;
	private String disponibilidadePeriodosString;

	private AvaliacaoEventoEntity avaliacaoEvento;

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTrabalhoDistancia() {
		return trabalhoDistancia;
	}

	public void setTrabalhoDistancia(String trabalhoDistancia) {
		this.trabalhoDistancia = trabalhoDistancia;
	}

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

	public String[] toStringArrayCausas(List<Causas> causas) {

		if (causas == null || causas.isEmpty()) {
			return null;
		}

		List<String> stringCausas = new ArrayList<>();
		for (Causas causa : causas) {
			stringCausas.add(causa.toString());
		}

		return stringCausas.toArray(new String[stringCausas.size()]);
	}

	public String[] toStringArrayHabilidades(List<Habilidades> habilidades) {

		if (habilidades == null || habilidades.isEmpty()) {
			return null;
		}

		List<String> stringVetor = new ArrayList<>();
		for (Habilidades habilidade : habilidades) {
			stringVetor.add(habilidade.toString());
		}

		return stringVetor.toArray(new String[stringVetor.size()]);
	}

	public String[] toStringArrayDiasSemana(List<DiasSemana> disponibilidadeDias) {

		if (disponibilidadeDias == null || disponibilidadeDias.isEmpty()) {
			return null;
		}

		List<String> stringVetor = new ArrayList<>();
		for (DiasSemana dias : disponibilidadeDias) {
			stringVetor.add(dias.toString());
		}

		return stringVetor.toArray(new String[stringVetor.size()]);
	}

	public String[] toStringArrayPeriodos(List<Periodos> disponibilidadePeriodos) {

		if (disponibilidadePeriodos == null || disponibilidadePeriodos.isEmpty()) {
			return null;
		}

		List<String> stringVetor = new ArrayList<>();
		for (Periodos p : disponibilidadePeriodos) {
			stringVetor.add(p.toString());
		}

		return stringVetor.toArray(new String[stringVetor.size()]);
	}

	public String getHabilidadesString() {
		return habilidadesString;
	}

	public void setHabilidadesString(String habilidadesString) {
		this.habilidadesString = habilidadesString;
	}

	public String getCausasString() {
		return causasString;
	}

	public void setCausasString(String causasString) {
		this.causasString = causasString;
	}

	public String getDisponibilidadeDiasString() {
		return disponibilidadeDiasString;
	}

	public void setDisponibilidadeDiasString(String disponibilidadeDiasString) {
		this.disponibilidadeDiasString = disponibilidadeDiasString;
	}

	public String getDisponibilidadePeriodosString() {
		return disponibilidadePeriodosString;
	}

	public void setDisponibilidadePeriodosString(String disponibilidadePeriodosString) {
		this.disponibilidadePeriodosString = disponibilidadePeriodosString;
	}

	public AvaliacaoEventoEntity getAvaliacaoEvento() {
		return avaliacaoEvento;
	}

	public void setAvaliacaoEvento(AvaliacaoEventoEntity avaliacaoEvento) {
		this.avaliacaoEvento = avaliacaoEvento;
	}

}