package br.com.helpthenext.repository;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.helpthenext.model.ONGModel;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.repository.entity.UsuarioEntity;
import br.com.helpthenext.uteis.Uteis;

public class ONGRepository {
	
	@Inject
	ONGEntity ongEntity;
 
	EntityManager entityManager;
 
	public void salvarNovoRegistro(ONGModel ongModel){
		
		entityManager =  Uteis.JpaEntityManager();
 
		ongEntity = new ONGEntity();
		ongEntity.setDataCadastro(LocalDateTime.now());
		ongEntity.setEmail(ongModel.getEmail());
		ongEntity.setNomeONG(ongModel.getNomeONG());
		ongEntity.setNomeResponsavel(ongModel.getNomeResponsavel());
		ongEntity.setTelefone(ongModel.getTelefone());
		ongEntity.setDescricao(ongModel.getDescricao());
		//cauas
		ongEntity.setWebsite(ongModel.getWebsite());
		ongEntity.setFacebook(ongModel.getFacebook());
		ongEntity.setNumero(ongModel.getNumero());
		ongEntity.setComplemento(ongModel.getComplemento());
		ongEntity.setCep(ongModel.getCep());
		ongEntity.setCidade(ongModel.getCidade());
		ongEntity.setPais(ongModel.getPais());
		ongEntity.setFoto(ongModel.getFoto());
		
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setSenha(ongModel.getUsuarioEntity().getSenha());
		usuarioEntity.setTipoUsuario(ongModel.getUsuarioEntity().getTipoUsuario());
		usuarioEntity.setUsuario(ongModel.getUsuarioEntity().getUsuario());
		entityManager.persist(usuarioEntity);

		ongEntity.setUsuarioEntity(usuarioEntity);
		
		entityManager.persist(ongEntity);
	}

}
