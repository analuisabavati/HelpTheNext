package br.com.helpthenext.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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

	@Inject
	ONGRepository ongRepository;

	@Inject
	VoluntarioRepository voluntarioRepository;

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
		eventoEntity.setNomeResponsavel(eventoModel.getNomeResponsavel());
		eventoEntity.setDataHora(eventoModel.getDataHora());
		eventoEntity.setLocal(eventoModel.getLocal());
		eventoEntity.setEmail(eventoModel.getEmail());
		eventoEntity.setBanner(eventoModel.getBanner());
		eventoEntity.setDataCadastro(LocalDateTime.now());

		eventoEntity.setCausa(Causas.valueOf(eventoModel.getCausa()));

		eventoEntity.setVoluntarioEntity(voluntarioRepository.getVoluntarioByUsuarioSessao());
		eventoEntity.setOngEntity(ongRepository.getONGByUsuarioSessao());

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
			eventoModel.setNomeResponsavel(eventoEntity.getNomeResponsavel());
			eventoModel.setDataHora(eventoEntity.getDataHora());
			eventoModel.setLocal(eventoEntity.getLocal());
			eventoModel.setEmail(eventoEntity.getEmail());
			eventoModel.setBanner(eventoEntity.getBanner());
			eventoModel.setDataCadastro(eventoEntity.getDataCadastro());

			eventoModel.setCausa(eventoEntity.getCausa().toString());

			eventoModel.setCausasString(eventoModel.getCausa());
			eventoModel.setDataCadastroDate(asDate(eventoModel.getDataCadastro()));

			eventoModel.setOngEntity(eventoEntity.getOngEntity());
			eventoModel.setVoluntarioEntity(eventoEntity.getVoluntarioEntity());

			eventosModel.add(eventoModel);
		}

		return eventosModel;
	}

	public Date asDate(LocalDateTime localDateTime) {
		if (localDateTime != null) {
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		}
		return null;
	}

	public void atualizarEvento(EventoModel eventoModel) {

		entityManager = Uteis.JpaEntityManager();

		eventoEntity = getEvento(eventoModel.getId());
		eventoEntity.setTitulo(eventoModel.getTitulo());
		eventoEntity.setDescricao(eventoModel.getDescricao());
		eventoEntity.setNomeResponsavel(eventoModel.getNomeResponsavel());
		eventoEntity.setDataHora(eventoModel.getDataHora());
		eventoEntity.setLocal(eventoModel.getLocal());
		eventoEntity.setEmail(eventoModel.getEmail());
		eventoEntity.setBanner(eventoModel.getBanner());

		eventoEntity.setCausa(Causas.valueOf(eventoModel.getCausa()));

		entityManager.merge(eventoEntity);
	}

	public void removeEvento(EventoModel evento) {
		entityManager = Uteis.JpaEntityManager();
		eventoEntity = getEvento(evento.getId());
		entityManager.remove(eventoEntity);
	}

	public void removeEvento(EventoEntity evento) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(evento);
	}

	public EventoEntity toEventoEntity(EventoModel eventoModel) {

		EventoEntity eventoEntity = getEvento(eventoModel.getId());
		eventoEntity.setTitulo(eventoModel.getTitulo());
		eventoEntity.setDescricao(eventoModel.getDescricao());
		eventoEntity.setNomeResponsavel(eventoModel.getNomeResponsavel());
		eventoEntity.setDataHora(eventoModel.getDataHora());
		eventoEntity.setLocal(eventoModel.getLocal());
		eventoEntity.setEmail(eventoModel.getEmail());
		eventoEntity.setBanner(eventoModel.getBanner());

		eventoEntity.setCausa(Causas.valueOf(eventoModel.getCausa()));

		eventoEntity.setVoluntarioEntity(voluntarioRepository.getVoluntarioByUsuarioSessao());
		eventoEntity.setOngEntity(ongRepository.getONGByUsuarioSessao());

		return eventoEntity;
	}

	public EventoModel toEventoModel(EventoEntity eventoEntity) {

		EventoModel eventoModel = new EventoModel();
		eventoModel.setId(eventoEntity.getId());
		eventoModel.setTitulo(eventoEntity.getTitulo());
		eventoModel.setDescricao(eventoEntity.getDescricao());
		eventoModel.setNomeResponsavel(eventoEntity.getNomeResponsavel());
		eventoModel.setDataHora(eventoEntity.getDataHora());
		eventoModel.setLocal(eventoEntity.getLocal());
		eventoModel.setEmail(eventoEntity.getEmail());
		eventoModel.setBanner(eventoEntity.getBanner());

		eventoModel.setCausa(eventoEntity.getCausa().toString());

		eventoModel.setCausasString(eventoModel.getCausa());
		eventoModel.setDataCadastroDate(asDate(eventoModel.getDataCadastro()));

		eventoModel.setOngEntity(eventoEntity.getOngEntity());
		eventoModel.setVoluntarioEntity(eventoEntity.getVoluntarioEntity());

		return eventoModel;
	}

}
