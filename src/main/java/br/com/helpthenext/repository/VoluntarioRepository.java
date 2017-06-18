package br.com.helpthenext.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.helpthenext.controller.UsuarioController;
import br.com.helpthenext.enums.Causas;
import br.com.helpthenext.enums.DiasSemana;
import br.com.helpthenext.enums.Habilidades;
import br.com.helpthenext.enums.Periodos;
import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.entity.UsuarioEntity;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.uteis.Uteis;

public class VoluntarioRepository {

	@Inject
	VoluntarioEntity voluntarioEntity;

	@Inject
	UsuarioEntity usuarioEntity;

	@Inject
	UsuarioController usuarioController;

	EntityManager entityManager;

	// Cadastra novo Voluntario
	public void salvarNovoRegistro(VoluntarioModel voluntarioModel) {

		entityManager = Uteis.JpaEntityManager();

		voluntarioEntity = new VoluntarioEntity();
		voluntarioEntity.setDataCadastro(LocalDateTime.now());
		voluntarioEntity.setEmail(voluntarioModel.getEmail());
		voluntarioEntity.setNome(voluntarioModel.getNome());
		voluntarioEntity.setSexo(voluntarioModel.getSexo());
		voluntarioEntity.setDataNascimento(voluntarioModel.getDataNascimento());
		voluntarioEntity.setFoto(voluntarioModel.getFotoPerfil());
		voluntarioEntity.setPais(voluntarioModel.getPais());
		voluntarioEntity.setEstado(voluntarioModel.getEstado());
		voluntarioEntity.setCidade(voluntarioModel.getCidade());
		voluntarioEntity.setCep(voluntarioModel.getCep());
		voluntarioEntity.setComplemento(voluntarioModel.getComplemento());
		voluntarioEntity.setNumero(voluntarioModel.getNumero());
		voluntarioEntity.setRua(voluntarioModel.getRua());
		voluntarioEntity.setTelefone(voluntarioModel.getTelefone());
		voluntarioEntity.setRg(voluntarioModel.getRg());
		voluntarioEntity.setCpf(voluntarioModel.getCpf());
		voluntarioEntity.setSobrenome(voluntarioModel.getSobrenome());
		voluntarioEntity.setTrabalhoDistancia(voluntarioModel.getTrabalhoDistancia());


		List<Causas> causas = new ArrayList<>();
		for (String p4 : voluntarioModel.getCausas()) {
			causas.add(Causas.values()[new Integer(p4)]);
		}
		voluntarioEntity.setCausas(causas);

		List<Habilidades> hab = new ArrayList<>();
		for (String p3 : voluntarioModel.getHabilidades()) {
			hab.add(Habilidades.values()[new Integer(p3)]);
		}
		voluntarioEntity.setHabilidades(hab);

		List<DiasSemana> dias = new ArrayList<>();
		for (String p1 : voluntarioModel.getDisponibilidadeDias()) {
			dias.add(DiasSemana.values()[new Integer(p1)]);
		}
		voluntarioEntity.setDiasDisponiveis(dias);

		List<Periodos> periodos = new ArrayList<>();
		for (String p : voluntarioModel.getDisponibilidadePeriodos()) {
			periodos.add(Periodos.values()[new Integer(p)]);
		}
		voluntarioEntity.setPeriodosDisponiveis(periodos);

		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setSenha(voluntarioModel.getUsuarioEntity().getSenha());
		usuarioEntity.setTipoUsuario(voluntarioModel.getUsuarioEntity().getTipoUsuario());
		usuarioEntity.setUsuario(voluntarioModel.getUsuarioEntity().getUsuario());
		entityManager.persist(usuarioEntity);

		voluntarioEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(voluntarioEntity);
	}

	private VoluntarioEntity getVoluntario(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(VoluntarioEntity.class, id);
	}

	public void atualizarVoluntario(VoluntarioModel voluntarioModel) {

		entityManager = Uteis.JpaEntityManager();

		VoluntarioEntity voluntarioEntity = getVoluntario(voluntarioModel.getId());

		voluntarioEntity.setEmail(voluntarioModel.getEmail());
		voluntarioEntity.setNome(voluntarioModel.getNome());
		voluntarioEntity.setSexo(voluntarioModel.getSexo());
		voluntarioEntity.setDataNascimento(voluntarioModel.getDataNascimento());
		voluntarioEntity.setFoto(voluntarioModel.getFotoPerfil());
		voluntarioEntity.setPais(voluntarioModel.getPais());
		voluntarioEntity.setEstado(voluntarioModel.getEstado());
		voluntarioEntity.setCidade(voluntarioModel.getCidade());
		voluntarioEntity.setCep(voluntarioModel.getCep());
		voluntarioEntity.setComplemento(voluntarioModel.getComplemento());
		voluntarioEntity.setNumero(voluntarioModel.getNumero());
		voluntarioEntity.setRua(voluntarioModel.getRua());
		voluntarioEntity.setTelefone(voluntarioModel.getTelefone());
		voluntarioEntity.setRg(voluntarioModel.getRg());
		voluntarioEntity.setCpf(voluntarioModel.getCpf());
		voluntarioEntity.setSobrenome(voluntarioModel.getSobrenome());
		voluntarioEntity.setTrabalhoDistancia(voluntarioModel.getTrabalhoDistancia());

		List<Causas> causas = new ArrayList<>();
		for (String p4 : voluntarioModel.getCausas()) {
			causas.add(Causas.values()[new Integer(p4)]);
		}
		voluntarioEntity.setCausas(causas);

		List<Habilidades> hab = new ArrayList<>();
		for (String p3 : voluntarioModel.getHabilidades()) {
			hab.add(Habilidades.values()[new Integer(p3)]);
		}
		voluntarioEntity.setHabilidades(hab);

		List<DiasSemana> dias = new ArrayList<>();
		for (String p1 : voluntarioModel.getDisponibilidadeDias()) {
			dias.add(DiasSemana.values()[new Integer(p1)]);
		}
		voluntarioEntity.setDiasDisponiveis(dias);

		List<Periodos> periodos = new ArrayList<>();
		for (String p : voluntarioModel.getDisponibilidadePeriodos()) {
			periodos.add(Periodos.values()[new Integer(p)]);
		}
		voluntarioEntity.setPeriodosDisponiveis(periodos);

		entityManager.merge(voluntarioEntity);
	}

	public VoluntarioEntity getVoluntarioByUsuarioSessao() {

		UsuarioEntity usuario = usuarioEntity.fromUsuarioModel(usuarioController.GetUsuarioSession());

		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("VoluntarioEntity.findByUsuario");
		query.setParameter("usuario", usuario);

		try {
			return (VoluntarioEntity) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
