package br.com.helpthenext.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.repository.entity.VagaEntity;
import br.com.helpthenext.uteis.Uteis;

public class VagaRepository {

	@Inject
	VagaEntity vagaEntity;

	EntityManager entityManager;

	// Cadastra novo Voluntario
	public void salvarNovoRegistro(VagaModel vagaModel) {
		
		entityManager = Uteis.JpaEntityManager();

		vagaEntity = new VagaEntity();
		vagaEntity.setTitulo(vagaModel.getTitulo());
		vagaEntity.setDescricao(vagaModel.getDescricao());
		vagaEntity.setNomeResponsavel(vagaModel.getDescricao());
		vagaEntity.setEmail(vagaModel.getEmail());
		vagaEntity.setBanner(vagaModel.getBanner());
		// causas e habilidades
		
		vagaEntity.setOngEntity(vagaModel.getOngEntity());

		entityManager.persist(vagaEntity);
	}

	//busca todas vagas no bd
	public List<VagaModel> getVagas() {

		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("VagaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<VagaEntity> vagasEntity = (Collection<VagaEntity>) query.getResultList();

		List<VagaModel> vagasModel = new ArrayList<VagaModel>();
		VagaModel vagaModel = null;

		for (VagaEntity vagaEntity : vagasEntity) {
			vagaModel = new VagaModel();
		
			vagaModel.setTitulo(vagaEntity.getTitulo());
			vagaModel.setDescricao(vagaEntity.getDescricao());
			vagaModel.setNomeResponsavel(vagaEntity.getNomeResponsavel());
			vagaModel.setEmail(vagaEntity.getEmail());

			vagasModel.add(vagaModel);
		}

		return vagasModel;
	}
}
