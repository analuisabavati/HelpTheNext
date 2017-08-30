package br.com.helpthenext.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.helpthenext.repository.entity.AvaliacaoEventoEntity;
import br.com.helpthenext.uteis.Uteis;

public class AvaliacaoEventoRepository {

	EntityManager entityManager;
	
	@Inject
	EventoRepository eventoRepository;
	
	@Inject
	VoluntarioRepository voluntarioRepository;

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

	public AvaliacaoEventoEntity getByIdVoluntarioIdEvento(Long idVoluntario, Long idEvento) {
		Query query = Uteis.JpaEntityManager().createNamedQuery("AvaliacaoEventoEntity.findByIdVoluntarioIdEvento");

		query.setParameter("idVoluntario", idVoluntario);
		query.setParameter("idEvento", idEvento);
		
		AvaliacaoEventoEntity avaliacao = (AvaliacaoEventoEntity) query.getSingleResult(); 
		if (avaliacao == null) {
			avaliacao = new AvaliacaoEventoEntity();
			avaliacao.setAvaliacao(0);
			avaliacao.setEvento(eventoRepository.getEvento(idEvento));
			avaliacao.setVoluntario(voluntarioRepository.getVoluntarioByUsuarioSessao());
		}

		return avaliacao;
	}

	public void salvarAtualizarAvaliacaoEvento(AvaliacaoEventoEntity avaliacao) {

		entityManager = Uteis.JpaEntityManager();

		entityManager.merge(avaliacao);
	}
}
