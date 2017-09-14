package br.com.helpthenext.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.helpthenext.repository.entity.AvaliacaoEventoEntity;
import br.com.helpthenext.uteis.Uteis;

public class AvaliacaoEventoRepository {
	
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<AvaliacaoEventoEntity> findEventosAvaliados(Long id) {
		Query query = Uteis.JpaEntityManager().createNamedQuery("AvaliacaoEventoEntity.findEventosAvaliados");

		query.setParameter("idVoluntario", id);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AvaliacaoEventoEntity> findByIdVoluntario(Long id) {
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

	@SuppressWarnings("unchecked")
	public AvaliacaoEventoEntity findByVoluntarioEvento(Long idVol,
			Long idEvento) {
		try {
			Query query = Uteis.JpaEntityManager().createNamedQuery("AvaliacaoEventoEntity.findByVoluntarioEvento");

			query.setParameter("idVoluntario", idVol);
			query.setParameter("idEvento", idEvento);
			
			List<AvaliacaoEventoEntity> result = query.getResultList();
			
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
			
			return null;

		} catch (NoResultException nre) {
			return null;
		} 

	}

	public void salvarAtualizarAvaliacaoEvento(AvaliacaoEventoEntity avaliacao) {
		entityManager = Uteis.JpaEntityManager();
		
		
		AvaliacaoEventoEntity existe = findByVoluntarioEvento(avaliacao.getIdVoluntario(), avaliacao.getIdEvento());
		if (existe != null) {
			avaliacao.setId(existe.getId());
		}

		entityManager.merge(avaliacao);
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<AvaliacaoEventoEntity> findAll() {
		Query query = Uteis.JpaEntityManager().createNamedQuery("AvaliacaoEventoEntity.findAll");

		List<AvaliacaoEventoEntity> avaliacoes;
		try {
			avaliacoes = query.getResultList();
		} catch (Exception e) {
			avaliacoes = new ArrayList<>();
		}
		return avaliacoes;
	}


}
