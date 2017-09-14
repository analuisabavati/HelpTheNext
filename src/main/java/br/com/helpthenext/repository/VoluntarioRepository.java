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

	@SuppressWarnings("unchecked")
	public List<Long> getIdsVoluntarios() {
		try {
			entityManager = Uteis.JpaEntityManager();
			Query query = entityManager.createNamedQuery("VoluntarioEntity.getIds");

			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

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

	public VoluntarioEntity getVoluntario(Long id) {
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

		UsuarioEntity usuarioEntity = voluntarioEntity.getUsuarioEntity();
		usuarioEntity.setSenha(voluntarioModel.getSenha());
		entityManager.merge(usuarioEntity);

		voluntarioEntity.setUsuarioEntity(usuarioEntity);

		entityManager.merge(voluntarioEntity);
	}

	public Long findByIdVoluntarioSessao() {

		UsuarioEntity usuario = usuarioEntity.fromUsuarioModel(usuarioController.GetUsuarioSession());

		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("VoluntarioEntity.findByUsuario");
		query.setParameter("usuario", usuario);

		try {
			VoluntarioEntity v = (VoluntarioEntity) query.getSingleResult();
			return v.getId();
		} catch (Exception e) {
			return null;
		}
	}

	public VoluntarioEntity findVoluntarioByUsuarioSessao() {

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

	public void atualizarVoluntario(VoluntarioEntity voluntarioEntity) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.merge(voluntarioEntity);
		entityManager.flush();
	}

	public VoluntarioEntity toVoluntarioEntity(VoluntarioModel voluntarioModel) {

		voluntarioEntity = getVoluntario(voluntarioModel.getId());
		voluntarioEntity.setEmail(voluntarioModel.getEmail());
		voluntarioEntity.setNome(voluntarioModel.getNome());
		voluntarioEntity.setSexo(voluntarioModel.getSexo());
		voluntarioEntity.setDataNascimento(voluntarioModel.getDataNascimento());
		voluntarioEntity.setFoto(voluntarioModel.getFotoPerfil());
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

		UsuarioEntity usuarioEntity = voluntarioEntity.getUsuarioEntity();
		usuarioEntity.setSenha(voluntarioModel.getUsuarioEntity().getSenha());
		usuarioEntity.setTipoUsuario(voluntarioModel.getUsuarioEntity().getTipoUsuario());
		usuarioEntity.setUsuario(voluntarioModel.getUsuarioEntity().getUsuario());
		voluntarioEntity.setUsuarioEntity(usuarioEntity);

		return voluntarioEntity;
	}

	public VoluntarioModel toVoluntarioModel(VoluntarioEntity voluntarioEntity) {

		entityManager = Uteis.JpaEntityManager();

		VoluntarioModel voluntarioModel = new VoluntarioModel();
		voluntarioModel.setId(voluntarioEntity.getId());
		voluntarioModel.setEmail(voluntarioEntity.getEmail());
		voluntarioModel.setNome(voluntarioEntity.getNome());
		voluntarioModel.setSexo(voluntarioEntity.getSexo());
		voluntarioModel.setDataNascimento(voluntarioEntity.getDataNascimento());
		voluntarioModel.setEstado(voluntarioEntity.getEstado());
		voluntarioModel.setCidade(voluntarioEntity.getCidade());
		voluntarioModel.setCep(voluntarioEntity.getCep());
		voluntarioModel.setComplemento(voluntarioEntity.getComplemento());
		voluntarioModel.setNumero(voluntarioEntity.getNumero());
		voluntarioModel.setRua(voluntarioEntity.getRua());
		voluntarioModel.setTelefone(voluntarioEntity.getTelefone());
		voluntarioModel.setRg(voluntarioEntity.getRg());
		voluntarioModel.setCpf(voluntarioEntity.getCpf());
		voluntarioModel.setSobrenome(voluntarioEntity.getSobrenome());
		voluntarioModel.setTrabalhoDistancia(voluntarioEntity.getTrabalhoDistancia());

		voluntarioModel.setCausas(voluntarioModel.toStringArrayCausas(voluntarioEntity.getCausas()));
		voluntarioModel.setHabilidades(voluntarioModel.toStringArrayHabilidades(voluntarioEntity.getHabilidades()));
		voluntarioModel
				.setDisponibilidadeDias(voluntarioModel.toStringArrayDiasSemana(voluntarioEntity.getDiasDisponiveis()));
		voluntarioModel.setDisponibilidadePeriodos(
				voluntarioModel.toStringArrayPeriodos(voluntarioEntity.getPeriodosDisponiveis()));

		voluntarioModel.setCausasString(voluntarioEntity.getCausas().toString());
		voluntarioModel.setHabilidadesString(voluntarioEntity.getHabilidades().toString());
		voluntarioModel.setDisponibilidadeDiasString(voluntarioEntity.getDiasDisponiveis().toString());
		voluntarioModel.setDisponibilidadePeriodosString(voluntarioEntity.getPeriodosDisponiveis().toString());

		UsuarioEntity usuarioEntity = voluntarioEntity.getUsuarioEntity();
		voluntarioModel.setUsuarioEntity(usuarioEntity);

		voluntarioModel.setSenha(usuarioEntity.getSenha());

		return voluntarioModel;
	}

	@SuppressWarnings("unchecked")
	public List<VoluntarioModel> findVoluntariosByCidadeEstado(String cidade, String estado) {
		try {

			Query query = Uteis.JpaEntityManager().createNamedQuery("VoluntarioEntity.findByCidadeEstado");

			query.setParameter("estado", estado);
			query.setParameter("cidade", cidade);

			List<VoluntarioEntity> list = query.getResultList();

			List<VoluntarioModel> model = new ArrayList<>();
			for (VoluntarioEntity voluntarioEntity : list) {
				model.add(toVoluntarioModel(voluntarioEntity));
			}

			return model;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<VoluntarioModel> findVoluntariosByTrabalhoDistancia() {
		try {

			Query query = Uteis.JpaEntityManager().createNamedQuery("VoluntarioEntity.findByTrabalhoDistancia");

			List<VoluntarioEntity> list = query.getResultList();

			List<VoluntarioModel> model = new ArrayList<>();
			for (VoluntarioEntity voluntarioEntity : list) {
				model.add(toVoluntarioModel(voluntarioEntity));
			}

			return model;
		} catch (Exception e) {
			return null;
		}
	}
}
