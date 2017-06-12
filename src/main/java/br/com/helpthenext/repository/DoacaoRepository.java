package br.com.helpthenext.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.helpthenext.model.DoacaoModel;
import br.com.helpthenext.repository.entity.DoacaoEntity;
import br.com.helpthenext.uteis.Uteis;

public class DoacaoRepository {

	@Inject
	DoacaoEntity doacaoEntity;

	EntityManager entityManager;

	// Cadastra novo Voluntario
	public void salvarNovoRegistro(DoacaoModel doacaoModel) {

		entityManager = Uteis.JpaEntityManager();

		doacaoEntity = new DoacaoEntity();
		doacaoEntity.setTitulo(doacaoModel.getTitulo());
		doacaoEntity.setDescricao(doacaoModel.getDescricao());
	

		entityManager.persist(doacaoEntity);
	}

	public List<DoacaoModel> getDoacaos() {
		
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("DoacaoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<DoacaoEntity> eventosEntity = (Collection<DoacaoEntity>) query.getResultList();

		List<DoacaoModel> doacoesModel = new ArrayList<DoacaoModel>();
		DoacaoModel eventoModel = null;

		for (DoacaoEntity vagaEntity : eventosEntity) {
			eventoModel = new DoacaoModel();
		
			eventoModel.setTitulo(vagaEntity.getTitulo());
			eventoModel.setDescricao(vagaEntity.getDescricao());

			doacoesModel.add(eventoModel);
		}

		return doacoesModel;
	}
}