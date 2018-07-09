package br.com.helpthenext.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.helpthenext.controller.UsuarioController;
import br.com.helpthenext.enums.Causas;
import br.com.helpthenext.model.ONGModel;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.repository.entity.UsuarioEntity;
import br.com.helpthenext.util.Uteis;

public class ONGRepository {

	@Inject
	private ONGEntity ongEntity;

	@Inject
	private UsuarioEntity usuarioEntity;

	@Inject
	private UsuarioController usuarioController;

	private EntityManager entityManager;

	public void salvarNovoRegistro(ONGModel ongModel) {

		entityManager = Uteis.JpaEntityManager();

		ongEntity = new ONGEntity();
		ongEntity.setDataCadastro(LocalDateTime.now());
		ongEntity.setEmail(ongModel.getEmail());
		ongEntity.setNomeONG(ongModel.getNomeONG());
		ongEntity.setNomeResponsavel(ongModel.getNomeResponsavel());
		ongEntity.setTelefone(ongModel.getTelefone());
		ongEntity.setDescricao(ongModel.getDescricao());

		List<Causas> causas = new ArrayList<>();
		for (String p4 : ongModel.getCausas()) {
			causas.add(Causas.values()[new Integer(p4)]);
		}
		ongEntity.setCausas(causas);

		ongEntity.setWebsite(ongModel.getWebsite());
		ongEntity.setFacebook(ongModel.getFacebook());
		ongEntity.setNumero(ongModel.getNumero());
		ongEntity.setComplemento(ongModel.getComplemento());
		ongEntity.setCep(ongModel.getCep());
		ongEntity.setCidade(ongModel.getCidade());
		ongEntity.setEstado(ongModel.getEstado());

		ongEntity.setFoto(ongModel.getFoto());

		UsuarioEntity usuarioEntity = ongModel.getUsuarioEntity();

		entityManager.persist(usuarioEntity);

		ongEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(ongEntity);
	}

	public ONGEntity findONGByUsuarioSessao() {
		entityManager = Uteis.JpaEntityManager();
		
		UsuarioEntity usuario = usuarioEntity.fromUsuarioModel(usuarioController.GetUsuarioSession());

		Query query = entityManager.createNamedQuery("ONGEntity.findByUsuario");
		query.setParameter("usuario", usuario);

		try {
			return (ONGEntity) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public ONGEntity findONGById(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(ONGEntity.class, id);
	}

	public void atualizarONG(ONGModel ongModel) {

		entityManager = Uteis.JpaEntityManager();

		ongEntity = findONGById(ongModel.getId());
		ongEntity.setEmail(ongModel.getEmail());
		ongEntity.setNomeONG(ongModel.getNomeONG());
		ongEntity.setNomeResponsavel(ongModel.getNomeResponsavel());
		ongEntity.setTelefone(ongModel.getTelefone());
		ongEntity.setDescricao(ongModel.getDescricao());

		List<Causas> causas = new ArrayList<>();
		for (String p4 : ongModel.getCausas()) {
			causas.add(Causas.values()[new Integer(p4)]);
		}
		ongEntity.setCausas(causas);

		ongEntity.setWebsite(ongModel.getWebsite());
		ongEntity.setFacebook(ongModel.getFacebook());
		ongEntity.setNumero(ongModel.getNumero());
		ongEntity.setComplemento(ongModel.getComplemento());
		ongEntity.setCep(ongModel.getCep());
		ongEntity.setCidade(ongModel.getCidade());
		ongEntity.setEstado(ongModel.getEstado());
		
		UsuarioEntity usuarioEntity = ongEntity.getUsuarioEntity();
		usuarioEntity.setSenha(ongModel.getSenhaONG());
		entityManager.merge(usuarioEntity);

		entityManager.merge(ongEntity);
	}
	
	public void atualizarONG(ONGEntity ongEntity) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.merge(ongEntity);
	}
	
	public ONGModel toONGModel(ONGEntity ongEntity) {
		entityManager = Uteis.JpaEntityManager();

		ONGModel model = new ONGModel();
		
		model.setId(ongEntity.getId());
		model.setEmail(ongEntity.getEmail());
		model.setNomeONG(ongEntity.getNomeONG());
		model.setNomeResponsavel(ongEntity.getNomeResponsavel());
		model.setTelefone(ongEntity.getTelefone());
		model.setDescricao(ongEntity.getDescricao());

		model.setCausas(model.toStringArrayCausas(ongEntity.getCausas()));

		model.setWebsite(ongEntity.getWebsite());
		model.setFacebook(ongEntity.getFacebook());
		model.setNumero(ongEntity.getNumero());
		model.setComplemento(ongEntity.getComplemento());
		model.setCep(ongEntity.getCep());
		model.setCidade(ongEntity.getCidade());
		model.setEstado(ongEntity.getEstado());
		model.setFoto(ongEntity.getFoto());
		
		model.setUsuarioEntity(ongEntity.getUsuarioEntity());

		model.setSenhaONG(ongEntity.getUsuarioEntity().getSenha());
		
		return model;
	}

	public void removerONG(ONGModel ong) {
		entityManager = Uteis.JpaEntityManager();
		
		UsuarioEntity us = entityManager.find(UsuarioEntity.class, ong.getUsuarioEntity().getId());
		entityManager.remove(us);	
		
		ONGEntity ongEntity = entityManager.find(ONGEntity.class, ong.getId());
		entityManager.remove(ongEntity);	
	}

}
