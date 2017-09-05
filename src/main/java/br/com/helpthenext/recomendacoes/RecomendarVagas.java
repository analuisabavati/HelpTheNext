package br.com.helpthenext.recomendacoes;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.recomendacoes.slopeOne.Predicoes;
import br.com.helpthenext.recomendacoes.slopeOne.SlopeOne;
import br.com.helpthenext.repository.AvaliacaoVagaRepository;
import br.com.helpthenext.repository.VagaRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.AvaliacaoVagaEntity;

@ViewScoped
@Named(value = "recomendarVagas")
public class RecomendarVagas implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String pathArquivoAvaliacoesVagas = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\avalicaoesVagas.dat";
	private static final String pathArquivoDiffVagas = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\diffVagas.txt";

	@Inject
	transient private VagaRepository vagaRepository;

	@Inject
	transient private VoluntarioRepository voluntarioRepository;

	@Inject
	transient private AvaliacaoVagaRepository avaliacaoVagaRepository;

	@Inject
	transient private SlopeOne slopeOne;

	@Inject
	transient private Predicoes predicoes;

	@Produces
	private List<VagaModel> vagasRecomendadas;
	
	private VagaModel selectedVaga;


	@PostConstruct
	public void init() {
		gerarArquivoAvaliacoesVagas();
		calcularMatrizDiferencasMedias();
		recomendaVagasConformePredicoes();
	}

	public void gerarArquivoAvaliacoesVagas() {
		List<AvaliacaoVagaEntity> avaliacoes = avaliacaoVagaRepository.findAll();

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(pathArquivoAvaliacoesVagas);

			for (AvaliacaoVagaEntity avaliacaoVagaEntity : avaliacoes) {
				fileOutputStream.write(String.valueOf(avaliacaoVagaEntity.getIdVoluntario()).getBytes());
				fileOutputStream.write(String.valueOf(",").getBytes());
				fileOutputStream.write(String.valueOf(avaliacaoVagaEntity.getIdVaga()).getBytes());
				fileOutputStream.write(String.valueOf(",").getBytes());
				fileOutputStream.write(String.valueOf(avaliacaoVagaEntity.getAvaliacao()).getBytes());
				fileOutputStream.write(String.valueOf("\n").getBytes());
			}
			
			fileOutputStream.write(String.valueOf("0").getBytes());

			fileOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void calcularMatrizDiferencasMedias() {
		gerarArquivoAvaliacoesVagas();
		slopeOne.calculaMatrizDiferencas(pathArquivoAvaliacoesVagas, pathArquivoDiffVagas);
	}

	public void recomendaVagasConformePredicoes() {
		Long idVoluntarioSessao = voluntarioRepository.getIdVoluntarioSessao();
		List<Long> idsVagas = predicoes.calculaPredicoes(idVoluntarioSessao.intValue(), pathArquivoAvaliacoesVagas, pathArquivoDiffVagas);
		vagasRecomendadas = vagaRepository.findByIds(idsVagas);
	}

	// -----------------------------------------------------------------------------------------------
	public List<VagaModel> getVagasRecomendadas() {
		return vagasRecomendadas;
	}

	public void setVagasRecomendadas(List<VagaModel> vagasRecomendadas) {
		this.vagasRecomendadas = vagasRecomendadas;
	}

	public VagaRepository getVagaRepository() {
		return vagaRepository;
	}

	public void setVagaRepository(VagaRepository vagaRepository) {
		this.vagaRepository = vagaRepository;
	}

	public AvaliacaoVagaRepository getAvaliacaoVagaRepository() {
		return avaliacaoVagaRepository;
	}

	public void setAvaliacaoVagaRepository(AvaliacaoVagaRepository avaliacaoVagaRepository) {
		this.avaliacaoVagaRepository = avaliacaoVagaRepository;
	}

	public SlopeOne getSlopeOne() {
		return slopeOne;
	}

	public void setSlopeOne(SlopeOne slopeOne) {
		this.slopeOne = slopeOne;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getPatharquivoavaliacoesvagas() {
		return pathArquivoAvaliacoesVagas;
	}

	public static String getPatharquivodiffvagas() {
		return pathArquivoDiffVagas;
	}

	public VoluntarioRepository getVoluntarioRepository() {
		return voluntarioRepository;
	}

	public void setVoluntarioRepository(VoluntarioRepository voluntarioRepository) {
		this.voluntarioRepository = voluntarioRepository;
	}

	public Predicoes getPredicoes() {
		return predicoes;
	}

	public void setPredicoes(Predicoes predicoes) {
		this.predicoes = predicoes;
	}

	public VagaModel getSelectedVaga() {
		return selectedVaga;
	}

	public void setSelectedVaga(VagaModel selectedVaga) {
		this.selectedVaga = selectedVaga;
	}
	
}
