package br.com.helpthenext.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.helpthenext.controller.UsuarioController;
import br.com.helpthenext.enums.DiasSemana;
import br.com.helpthenext.enums.Periodos;
import br.com.helpthenext.model.DoacaoModel;
import br.com.helpthenext.repository.entity.DoacaoEntity;
import br.com.helpthenext.uteis.Uteis;

public class DoacaoRepository {

	@Inject
	DoacaoEntity doacaoEntity;
	
	@Inject
	UsuarioController usuarioControoler;
	
	@Inject
	VoluntarioRepository voluntarioRepository;

	EntityManager entityManager;
	
	public DoacaoEntity getDoacao(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(DoacaoEntity.class, id);
	}

	public void salvarNovoRegistro(DoacaoModel doacaoModel) {

		entityManager = Uteis.JpaEntityManager();

		doacaoEntity = new DoacaoEntity();
		doacaoEntity.setTitulo(doacaoModel.getTitulo());
		doacaoEntity.setDescricao(doacaoModel.getDescricao());
		doacaoEntity.setFoto(doacaoModel.getFoto());
		doacaoEntity.setVoluntarioEntity(doacaoModel.getVoluntarioEntity());
		doacaoEntity.setDataCadastro(LocalDateTime.now());
		
		List<DiasSemana> dias = new ArrayList<>();
		for(String p1 : doacaoModel.getDias()){
			dias.add(DiasSemana.values()[new Integer(p1)]);
		}
		doacaoEntity.setDias(dias);
		
		List<Periodos> periodos = new ArrayList<>();
		for(String p : doacaoModel.getPeriodos()){
			periodos.add(Periodos.values()[new Integer(p)]);
		}
		doacaoEntity.setPeriodos(periodos);
		
		doacaoEntity.setVoluntarioEntity(voluntarioRepository.getVoluntarioByUsuarioSessao());
	
		entityManager.persist(doacaoEntity);
	}

	public List<DoacaoModel> getDoacaos() {
		
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("DoacaoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<DoacaoEntity> eventosEntity = (Collection<DoacaoEntity>) query.getResultList();

		List<DoacaoModel> doacoesModel = new ArrayList<DoacaoModel>();
		DoacaoModel doacaoModel = null;

		for (DoacaoEntity doacaoEntity : eventosEntity) {
			doacaoModel = new DoacaoModel();
		
			doacaoModel.setId(doacaoEntity.getId());
			doacaoModel.setTitulo(doacaoEntity.getTitulo());
			doacaoModel.setDescricao(doacaoEntity.getDescricao());
			doacaoModel.setVoluntarioEntity(doacaoEntity.getVoluntarioEntity());
			doacaoModel.setDataCadastro(doacaoEntity.getDataCadastro());
			
			doacaoModel.setPeriodos(doacaoModel.toStringArrayPeriodos(doacaoEntity.getPeriodos()));
			doacaoModel.setDias(doacaoModel.toStringArrayDias(doacaoEntity.getDias()));
			
			doacaoModel.setDiasString(doacaoModel.getDias() == null ? null : Arrays.toString(doacaoModel.getDias()));
			doacaoModel.setPeriodoString(doacaoModel.getPeriodos() == null ? null : Arrays.toString(doacaoModel.getPeriodos()));
			
			doacoesModel.add(doacaoModel);
		}

		return doacoesModel;
	}

	public void ataulizarDoacao(DoacaoModel doacaoModel) {

		entityManager = Uteis.JpaEntityManager();

		doacaoEntity = getDoacao(doacaoModel.getId());
		doacaoEntity.setTitulo(doacaoModel.getTitulo());
		doacaoEntity.setDescricao(doacaoModel.getDescricao());
		doacaoEntity.setFoto(doacaoModel.getFoto());
		doacaoEntity.setVoluntarioEntity(doacaoModel.getVoluntarioEntity());
		doacaoEntity.setDataCadastro(LocalDateTime.now());
		
		List<DiasSemana> dias = new ArrayList<>();
		for(String p1 : doacaoModel.getDias()){
			dias.add(DiasSemana.values()[new Integer(p1)]);
		}
		doacaoEntity.setDias(dias);
		
		List<Periodos> periodos = new ArrayList<>();
		for(String p : doacaoModel.getPeriodos()){
			periodos.add(Periodos.values()[new Integer(p)]);
		}
		doacaoEntity.setPeriodos(periodos);
			
		entityManager.merge(doacaoEntity);
	}
	
	public void removerDoacao(DoacaoModel doacao) {
		entityManager = Uteis.JpaEntityManager();
		doacaoEntity = getDoacao(doacao.getId());
		entityManager.remove(doacaoEntity);
	}

	public void removerDoacao(DoacaoEntity doacao) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(doacao);
	}

}

