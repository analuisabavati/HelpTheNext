package br.com.helpthenext.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.helpthenext.model.EventoModel;
import br.com.helpthenext.repository.entity.EventoEntity;
import br.com.helpthenext.uteis.Uteis;

public class EventoRepository {

	@Inject
	EventoEntity eventoEntity;

	EntityManager entityManager;

	// Cadastra novo Voluntario
	public void salvarNovoRegistro(EventoModel eventoModel) {

		entityManager = Uteis.JpaEntityManager();

		eventoEntity = new EventoEntity();
		eventoEntity.setTitulo(eventoModel.getTitulo());
		eventoEntity.setDescricao(eventoModel.getDescricao());
		eventoEntity.setNomeResponsavel(eventoModel.getDescricao());
		eventoEntity.setDataHora(eventoModel.getDataHora());
		eventoEntity.setLocal(eventoModel.getLocal());
		eventoEntity.setEmail(eventoModel.getEmail());
		eventoEntity.setBanner(eventoModel.getBanner());
		
		//causas
		
		entityManager.persist(eventoEntity);
	}

	public List<EventoModel> getEventos() {
		
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("EventoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<EventoEntity> eventosEntity = (Collection<EventoEntity>) query.getResultList();

		List<EventoModel> eventosModel = new ArrayList<EventoModel>();
		EventoModel eventoModel = null;

		for (EventoEntity vagaEntity : eventosEntity) {
			eventoModel = new EventoModel();
		
			eventoModel.setTitulo(vagaEntity.getTitulo());
			eventoModel.setDescricao(vagaEntity.getDescricao());
			eventoModel.setNomeResponsavel(vagaEntity.getNomeResponsavel());
			eventoModel.setEmail(vagaEntity.getEmail());

			eventosModel.add(eventoModel);
		}

		return eventosModel;
	}
}
