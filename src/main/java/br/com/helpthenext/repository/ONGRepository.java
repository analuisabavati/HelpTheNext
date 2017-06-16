package br.com.helpthenext.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.helpthenext.enums.Causas;
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
		
		List<Causas> causas = new ArrayList<>();
		for(String p4 : ongModel.getCausas()){
			causas.add(Causas.values()[new Integer(p4)]);
		}
		ongEntity.setCausas(causas);
		
		ongEntity.setWebsite(ongModel.getWebsite());
		ongEntity.setFacebook(ongModel.getFacebook());
		ongEntity.setNumero(ongModel.getNumero());
		ongEntity.setComplemento(ongModel.getComplemento());
		ongEntity.setCep(ongModel.getCep());
		ongEntity.setCidade(ongModel.getCidade());
		ongEntity.setPais(ongModel.getPais());
		ongEntity.setFoto(ongModel.getFoto());
		
		UsuarioEntity usuarioEntity = ongModel.getUsuarioEntity();
		
		entityManager.persist(usuarioEntity);

		ongEntity.setUsuarioEntity(usuarioEntity);
		
		entityManager.persist(ongEntity);
	}

}
