package br.com.helpthenext.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.repository.entity.VagaEntity;
import br.com.helpthenext.uteis.Uteis;

public class VagaRepository {

	@Inject
	VagaEntity vagaEntity;
 
	EntityManager entityManager;
	
	//Cadastra novo Voluntario 
		public void salvarNovoRegistro(VagaModel vagaModel){
	 
			entityManager =  Uteis.JpaEntityManager();
			
			vagaEntity= new VagaEntity();
			vagaEntity.setTitulo(vagaModel.getTitulo());
			vagaEntity.setDescricao(vagaModel.getDescricao());
			vagaEntity.setNomeResponsavel(vagaModel.getDescricao());
			
			entityManager.persist(vagaEntity);
		}
}
