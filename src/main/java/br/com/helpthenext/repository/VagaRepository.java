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
import br.com.helpthenext.enums.DiasSemana;
import br.com.helpthenext.enums.Habilidades;
import br.com.helpthenext.enums.Periodos;
import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.repository.entity.AvaliacaoVagaEntity;
import br.com.helpthenext.repository.entity.VagaEntity;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.uteis.Uteis;

public class VagaRepository {

	@Inject
	private VagaEntity vagaEntity;

	@Inject
	private ONGRepository ongRepository;
	
	@Inject
	private AvaliacaoVagaRepository avaliacaoVagaRepository;
	
	@Inject
	private VoluntarioRepository voluntarioRepository;

	private EntityManager entityManager;

	public VagaEntity getVaga(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(VagaEntity.class, id);
	}

	// Cadastra nova vaga
	public void salvarNovoRegistro(VagaModel vagaModel) {

		entityManager = Uteis.JpaEntityManager();

		vagaEntity = new VagaEntity();
		vagaEntity.setTitulo(vagaModel.getTitulo());
		vagaEntity.setDescricao(vagaModel.getDescricao());
		vagaEntity.setNomeResponsavel(vagaModel.getNomeResponsavel());
		vagaEntity.setEmail(vagaModel.getEmail());
		vagaEntity.setBanner(vagaModel.getBanner());
		vagaEntity.setDataCadastro(LocalDateTime.now());
		vagaEntity.setTrabalhoDistancia(vagaModel.getTrabalhoDistancia());

		List<Causas> causas = new ArrayList<>();
		for (String p4 : vagaModel.getCausas()) {
			causas.add(Causas.values()[new Integer(p4)]);
		}
		vagaEntity.setCausas(causas);

		List<Habilidades> hab = new ArrayList<>();
		for (String p3 : vagaModel.getHabilidades()) {
			hab.add(Habilidades.values()[new Integer(p3)]);
		}
		vagaEntity.setHabilidades(hab);

		List<DiasSemana> dias = new ArrayList<>();
		for (String p1 : vagaModel.getDias()) {
			dias.add(DiasSemana.values()[new Integer(p1)]);
		}
		vagaEntity.setDias(dias);

		List<Periodos> periodos = new ArrayList<>();
		for (String p : vagaModel.getPeriodos()) {
			periodos.add(Periodos.values()[new Integer(p)]);
		}

		vagaEntity.setPeriodos(periodos);

		vagaEntity.setOngEntity(ongRepository.getONGByUsuarioSessao());

		entityManager.persist(vagaEntity);
	}

	public List<VagaModel> getVagas() {

		entityManager = Uteis.JpaEntityManager();
		
		Query query = entityManager.createNamedQuery("VagaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<VagaEntity> vagasEntity = (Collection<VagaEntity>) query.getResultList();

		List<VagaModel> vagasModel = new ArrayList<VagaModel>();
		VagaModel vagaModel = null;

		for (VagaEntity vagaEntity : vagasEntity) {
			vagaModel = toVagaModel(vagasModel, vagaEntity);
			vagasModel.add(vagaModel);
		}

		return vagasModel;
	}

	private VagaModel toVagaModel(List<VagaModel> vagasModel, VagaEntity vagaEntity) {
		VagaModel vagaModel;
		vagaModel = new VagaModel();

		Long idVaga = vagaEntity.getId();
		vagaModel.setId(idVaga);
		vagaModel.setTitulo(vagaEntity.getTitulo());
		vagaModel.setDescricao(vagaEntity.getDescricao());
		vagaModel.setNomeResponsavel(vagaEntity.getNomeResponsavel());
		vagaModel.setEmail(vagaEntity.getEmail());
		vagaModel.setBanner(vagaEntity.getBanner());
		vagaModel.setDataCadastro(vagaEntity.getDataCadastro());
		vagaModel.setTrabalhoDistancia(vagaEntity.getTrabalhoDistancia());

		vagaModel.setCausas(vagaModel.toStringArrayCausas(vagaEntity.getCausas()));
		vagaModel.setHabilidades(vagaModel.toStringArrayHabilidades(vagaEntity.getHabilidades()));
		vagaModel.setPeriodos(vagaModel.toStringArrayPeriodos(vagaEntity.getPeriodos()));
		vagaModel.setDias(vagaModel.toStringArrayDias(vagaEntity.getDias()));
		vagaModel.setOngEntity(vagaEntity.getOngEntity());
		
		vagaModel.setCausasString(vagaModel.getCausas() == null ? null : Arrays.toString(vagaModel.getCausas()));
		vagaModel.setHabilidadesString(vagaModel.getHabilidades()  == null ? null : Arrays.toString(vagaModel.getHabilidades()));
		vagaModel.setDiasString(vagaModel.getDias() == null ? null : Arrays.toString(vagaModel.getDias()));
		vagaModel.setPeriodoString(vagaModel.getPeriodos() == null ? null : Arrays.toString(vagaModel.getPeriodos()));
		vagaModel.setDataCadastroDate(asDate(vagaModel.getDataCadastro()));
		
		vagaModel.setAvaliacaoVaga(getAvaliacaoEvento(vagaEntity, idVaga));

		return vagaModel;
	}
	

	public Date asDate(LocalDateTime localDateTime) {
		if (localDateTime != null) {
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		}
		return null;
	}

	public void atualizaVaga(VagaModel vagaModel) {

		entityManager = Uteis.JpaEntityManager();

		vagaEntity = getVaga(vagaModel.getId());

		vagaEntity.setTitulo(vagaModel.getTitulo());
		vagaEntity.setDescricao(vagaModel.getDescricao());
		vagaEntity.setNomeResponsavel(vagaModel.getDescricao());
		vagaEntity.setEmail(vagaModel.getEmail());
		vagaEntity.setBanner(vagaModel.getBanner());
		vagaEntity.setDataCadastro(LocalDateTime.now());
		vagaEntity.setTrabalhoDistancia(vagaModel.getTrabalhoDistancia());

		List<Causas> causas = new ArrayList<>();
		for (String p4 : vagaModel.getCausas()) {
			causas.add(Causas.values()[new Integer(p4)]);
		}
		vagaEntity.setCausas(causas);

		List<Habilidades> hab = new ArrayList<>();
		for (String p3 : vagaModel.getHabilidades()) {
			hab.add(Habilidades.values()[new Integer(p3)]);
		}
		vagaEntity.setHabilidades(hab);

		List<DiasSemana> dias = new ArrayList<>();
		for (String p1 : vagaModel.getDias()) {
			dias.add(DiasSemana.values()[new Integer(p1)]);
		}
		vagaEntity.setDias(dias);

		List<Periodos> periodos = new ArrayList<>();
		for (String p : vagaModel.getPeriodos()) {
			periodos.add(Periodos.values()[new Integer(p)]);
		}

		vagaEntity.setPeriodos(periodos);

		entityManager.merge(vagaEntity);
	}

	public void removeVaga(VagaModel vagaModel) {
		entityManager = Uteis.JpaEntityManager();
		vagaEntity = getVaga(vagaModel.getId());
		entityManager.remove(vagaEntity);
	}

	public void removeVaga(VagaEntity vaga) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(vaga);
	}
	
	private AvaliacaoVagaEntity getAvaliacaoEvento(VagaEntity VagaEntity, Long idVaga) {
		VoluntarioEntity voluntarioByUsuarioSessao = voluntarioRepository.getVoluntarioByUsuarioSessao();
		
		if (voluntarioByUsuarioSessao == null) {
			return new AvaliacaoVagaEntity();
		}

		AvaliacaoVagaEntity avaliacao = avaliacaoVagaRepository.findByVoluntarioVaga(voluntarioByUsuarioSessao.getId(),
				VagaEntity.getId());
		if (avaliacao == null) {
			avaliacao = new AvaliacaoVagaEntity();
			avaliacao.setAvaliacao(0);
			avaliacao.setIdVaga(VagaEntity.getId());
			avaliacao.setIdVoluntario(voluntarioByUsuarioSessao.getId());
		}
		return avaliacao;
	}
	

	@SuppressWarnings("unchecked")
	public List<VagaModel> findByIds(List<Long> ids) {
		try {
			
			if (ids == null || ids.isEmpty()) {
				return null;
			}
					
			entityManager = Uteis.JpaEntityManager();
			entityManager.joinTransaction();
			
			Query query = entityManager.createNamedQuery("VagaEntity.findByIds");
			query.setParameter("list", ids);

			List<VagaEntity> result = (List<VagaEntity>) query.getResultList();
			
			List<VagaModel> vagasModel = new ArrayList<VagaModel>();
			VagaModel vagaModel = null;

			for (VagaEntity vagaEntity : result) {
				vagaModel = toVagaModel(vagasModel, vagaEntity);
				vagasModel.add(vagaModel);
			}
	
			return vagasModel;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
}
