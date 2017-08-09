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
import br.com.helpthenext.uteis.Uteis;

public class ONGRepository {

	@Inject
	ONGEntity ongEntity;

	@Inject
	UsuarioEntity usuarioEntity;

	@Inject
	UsuarioController usuarioController;

	EntityManager entityManager;

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
		ongEntity.setFoto(ongModel.getFoto());

		UsuarioEntity usuarioEntity = ongModel.getUsuarioEntity();

		entityManager.persist(usuarioEntity);

		ongEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(ongEntity);
	}

	public ONGEntity getONGByUsuarioSessao() {

		UsuarioEntity usuario = usuarioEntity.fromUsuarioModel(usuarioController.GetUsuarioSession());

		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("ONGEntity.findByUsuario");
		query.setParameter("usuario", usuario);

		try {
			return (ONGEntity) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public ONGEntity getONG(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(ONGEntity.class, id);
	}

	public void atualizarOng(ONGModel ongModel) {

		entityManager = Uteis.JpaEntityManager();

		ongEntity = getONG(ongModel.getId());
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
		ongEntity.setFoto(ongModel.getFoto());
		
		UsuarioEntity usuarioEntity = ongEntity.getUsuarioEntity();
		usuarioEntity.setSenha(ongModel.getUsuarioEntity().getSenha());
		entityManager.merge(usuarioEntity);

		ongEntity.setUsuarioEntity(usuarioEntity);

		entityManager.merge(ongEntity);
	}
	
	public void atualizarONG(ONGEntity ongEntity) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.merge(ongEntity);
	}

}
