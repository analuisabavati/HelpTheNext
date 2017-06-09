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
	 * M�TODO RESPONS�VEL POR SALVAR UMA NOVO VOLUNTARIO
	 * @param voluntarioModel
	 */
	public void salvarNovoRegistro(VoluntarioModel voluntarioModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		voluntarioEntity = new VoluntarioEntity();
		voluntarioEntity.setDataCadastro(LocalDateTime.now());
		voluntarioEntity.setEmail(voluntarioModel.getEmail());
		voluntarioEntity.setNome(voluntarioModel.getNome());
		voluntarioEntity.setSexo(voluntarioModel.getSexo());
		voluntarioEntity.setDataNascimento(voluntarioModel.getDataNascimento());

	
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setSenha(voluntarioModel.getUsuarioEntity().getSenha());
		usuarioEntity.setTipoUsuario(voluntarioModel.getUsuarioEntity().getTipoUsuario());
		usuarioEntity.setUsuario(voluntarioModel.getUsuarioEntity().getUsuario());
		entityManager.persist(usuarioEntity);

		voluntarioEntity.setUsuarioEntity(usuarioEntity);
		
		entityManager.persist(voluntarioEntity);
	}
}
