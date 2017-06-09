package br.com.helpthenext.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.helpthenext.model.EventoModel;
import br.com.helpthenext.repository.entity.EventoEntity;
import br.com.helpthenext.uteis.Uteis;

public class EventoRepository {

	@Inject
	EventoEntity eventoEntity;
 
	EntityManager entityManager;
	
	//Cadastra novo Voluntario 
		public void salvarNovoRegistro(EventoModel eventoModel){
	 
			entityManager =  Uteis.JpaEntityManager();
			
			eventoEntity= new EventoEntity();
			eventoEntity.setTitulo(eventoModel.getTitulo());
			eventoEntity.setDescricao(eventoModel.getDescricao());
			eventoEntity.setNomeResponsavel(eventoModel.getDescricao());
			eventoEntity.setHorario(eventoModel.getHorario());
			eventoEntity.setLocal(eventoModel.getLocal());
			eventoEntity.setEmail(eventoModel.getEmail());
			
			entityManager.persist(eventoEntity);
		}
}
