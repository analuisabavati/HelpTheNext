package br.com.helpthenext.repository;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;
 
import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.entity.*;
import br.com.helpthenext.repository.entity.UsuarioEntity;
import br.com.helpthenext.uteis.Uteis;
 
public class VoluntarioRepository {
 
	@Inject
	VoluntarioEntity voluntarioEntity;
 
	EntityManager entityManager;
 
	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVO VOLUNTARIO
	 * @param voluntarioModel
	 */
	public void SalvarNovoRegistro(VoluntarioModel voluntarioModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		voluntarioEntity = new VoluntarioEntity();
		voluntarioEntity.setDataCadastro(LocalDateTime.now());
		voluntarioEntity.setEmail(voluntarioModel.getEmail());
		voluntarioEntity.setEndereco(voluntarioModel.getEndereco());
		voluntarioEntity.setNome(voluntarioModel.getNome());
		voluntarioEntity.setOrigemCadastro(voluntarioModel.getOrigemCadastro());
		voluntarioEntity.setSexo(voluntarioModel.getSexo());
 
		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, voluntarioModel.getUsuarioModel().getCodigo()); 
		 
		voluntarioEntity.setUsuarioEntity(usuarioEntity);
 
		entityManager.persist(voluntarioEntity);
	}
}
