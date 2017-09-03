package br.com.helpthenext.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.helpthenext.repository.entity.AvaliacaoVagaEntity;
import br.com.helpthenext.uteis.Uteis;

public class AvaliacaoVagaRepository {
	
	EntityManager entityManager;

	public void salvarAtualizarAvaliacaoVaga(AvaliacaoVagaEntity avaliacao) {
		entityManager = Uteis.JpaEntityManager();
		
		AvaliacaoVagaEntity novo = new AvaliacaoVagaEntity();
		novo.setIdVaga(avaliacao.getIdVaga());
		novo.setIdVoluntario(avaliacao.getIdVoluntario());
		novo.setAvaliacao(avaliacao.getAvaliacao());
		
		AvaliacaoVagaEntity existe = findByVoluntarioVaga(avaliacao.getIdVoluntario(), avaliacao.getIdVaga());
		if (existe != null) {
			avaliacao.setId(existe.getId());
		}

		novo.setId(avaliacao.getId());

		entityManager.merge(novo);
	}
	
	public AvaliacaoVagaEntity findByVoluntarioVaga(Long idVol, Long idVaga) {
		try {
			Query query = Uteis.JpaEntityManager().createNamedQuery("AvaliacaoVagaEntity.findByVoluntarioVaga");

			query.setParameter("idVoluntario", idVol);
			query.setParameter("idVaga", idVaga);

			return (AvaliacaoVagaEntity) query.getSingleResult();

		} catch (NoResultException nre) {
			return null;
		}

	}
	
}
