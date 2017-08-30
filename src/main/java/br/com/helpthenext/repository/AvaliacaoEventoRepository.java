package br.com.helpthenext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.helpthenext.repository.entity.AvaliacaoEventoEntity;
import br.com.helpthenext.repository.entity.EventoEntity;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.uteis.Uteis;

public class AvaliacaoEventoRepository {

	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<AvaliacaoEventoEntity> getByIdVoluntario(Long id) {
		Query query = Uteis.JpaEntityManager().createNamedQuery("AvaliacaoEventoEntity.findByIdVoluntario");

		query.setParameter("id", id);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AvaliacaoEventoEntity> getByIdEvento(Long id) {
		Query query = Uteis.JpaEntityManager().createNamedQuery("AvaliacaoEventoEntity.findByIdEvento");

		query.setParameter("id", id);

		return query.getResultList();
	}

	public AvaliacaoEventoEntity findByVoluntarioEvento(VoluntarioEntity voluntarioByUsuarioSessao,
			EventoEntity eventoEntity) {
		try {
			Query query = Uteis.JpaEntityManager().createNamedQuery("AvaliacaoEventoEntity.findByVoluntarioEvento");

			query.setParameter("idVoluntario", voluntarioByUsuarioSessao);
			query.setParameter("idEvento", eventoEntity);

			return (AvaliacaoEventoEntity) query.getSingleResult();

		} catch (NoResultException nre) {
			return null;
		}

	}

	public void salvarAtualizarAvaliacaoEvento(AvaliacaoEventoEntity avaliacao) {
		entityManager = Uteis.JpaEntityManager();
		
		
			entityManager.merge(avaliacao);

	}
}
