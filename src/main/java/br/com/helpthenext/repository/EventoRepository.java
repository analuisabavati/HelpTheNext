package br.com.helpthenext.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.helpthenext.enums.Causas;
import br.com.helpthenext.model.EventoModel;
import br.com.helpthenext.repository.entity.EventoEntity;
import br.com.helpthenext.uteis.Uteis;

public class EventoRepository {

	@Inject
	EventoEntity eventoEntity;

	EntityManager entityManager;

	public EventoEntity getEvento(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(EventoEntity.class, id);
	}

	// Cadastra novo evento
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
		eventoEntity.setDataCadastro(LocalDateTime.now());

		List<Causas> causas = new ArrayList<>();
		for (String p4 : eventoModel.getCausas()) {
			causas.add(Causas.values()[new Integer(p4)]);
		}
		eventoEntity.setCausas(causas);

		eventoEntity.setOngEntity(eventoModel.getOngEntity());
		eventoEntity.setVoluntarioEntity(eventoModel.getVoluntarioEntity());

		entityManager.persist(eventoEntity);
	}

	public List<EventoModel> getEventos() {

		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("EventoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<EventoEntity> eventosEntity = (Collection<EventoEntity>) query.getResultList();

		List<EventoModel> eventosModel = new ArrayList<>();
		EventoModel eventoModel = null;

		for (EventoEntity eventoEntity : eventosEntity) {
			eventoModel = new EventoModel();

			eventoModel.setId(eventoEntity.getId());
			eventoModel.setTitulo(eventoEntity.getTitulo());
			eventoModel.setDescricao(eventoEntity.getDescricao());
			eventoModel.setNomeResponsavel(eventoEntity.getDescricao());
			eventoModel.setDataHora(eventoEntity.getDataHora());
			eventoModel.setLocal(eventoEntity.getLocal());
			eventoModel.setEmail(eventoEntity.getEmail());
			eventoModel.setBanner(eventoEntity.getBanner());

			eventoModel.setBanner(eventoEntity.getBanner());

			/*
			 * String[] stockArr = new String[vagaEntity.getCausas().size()];
			 * stockArr = vagaEntity.getCausas().toArray(stockArr);
			 * 
			 * eventoModel.setCausas(stockArr);
			 */
			eventoModel.setOngEntity(eventoEntity.getOngEntity());
			eventoModel.setVoluntarioEntity(eventoEntity.getVoluntarioEntity());

			eventosModel.add(eventoModel);
		}

		return eventosModel;
	}
}
