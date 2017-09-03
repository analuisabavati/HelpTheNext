package br.com.helpthenext.recomendacoes;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.EventoModel;
import br.com.helpthenext.recomendacoes.slopeOne.Predicoes;
import br.com.helpthenext.recomendacoes.slopeOne.SlopeOne;
import br.com.helpthenext.repository.AvaliacaoEventoRepository;
import br.com.helpthenext.repository.EventoRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.AvaliacaoEventoEntity;

@ViewScoped
@Named(value = "recomendarVagas")
public class RecomendarEventos implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String pathArquivoAvaliacoesEventos = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\avalicaoesEventos.dat";
	private static final String pathArquivoDiffEventos = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\diffEventos.txt";

	@Inject
	transient private EventoRepository eventoRepository;

	@Inject
	transient private AvaliacaoEventoRepository avaliacaoEventoRepository;

	@Inject
	transient private VoluntarioRepository voluntarioRepository;

	@Inject
	transient private SlopeOne slopeOne;

	@Inject
	transient private Predicoes predicoes;

	@Produces
	private List<EventoModel> eventosRecomendados;

	@PostConstruct
	public void init() {
		gerarArquivoAvaliacoesEventos();
		calcularMatrizDiferencas();
	}

	public void gerarArquivoAvaliacoesEventos() {
		List<AvaliacaoEventoEntity> avaliacoes = avaliacaoEventoRepository.findAll();

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(pathArquivoAvaliacoesEventos);

			for (AvaliacaoEventoEntity a : avaliacoes) {
				fileOutputStream.write(String.valueOf(a.getIdVoluntario()).getBytes());
				fileOutputStream.write(String.valueOf(",").getBytes());
				fileOutputStream.write(String.valueOf(a.getIdEvento()).getBytes());
				fileOutputStream.write(String.valueOf(",").getBytes());
				fileOutputStream.write(String.valueOf(a.getAvaliacao()).getBytes());
				fileOutputStream.write(String.valueOf("\n").getBytes());
			}

			fileOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void calcularMatrizDiferencas() {
		gerarArquivoAvaliacoesEventos();
		slopeOne.calculaMatrizDiferencas(pathArquivoAvaliacoesEventos, pathArquivoDiffEventos);
	}

	public void recomendaEventosConformePredicoes() {
		Long idVoluntarioSessao = voluntarioRepository.getIdVoluntarioSessao();
		List<Long> idsVagas = predicoes.calculaPredicoes(idVoluntarioSessao.intValue(), pathArquivoAvaliacoesEventos, pathArquivoDiffEventos);

		// fazer busca dos itens e tela 
	}

	// -----------------------------------------------------------------------------------------------
	public EventoRepository getEventoRepository() {
		return eventoRepository;
	}

	public void setEventoRepository(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}

	public AvaliacaoEventoRepository getAvaliacaoEventoRepository() {
		return avaliacaoEventoRepository;
	}

	public void setAvaliacaoEventoRepository(AvaliacaoEventoRepository avaliacaoEventoRepository) {
		this.avaliacaoEventoRepository = avaliacaoEventoRepository;
	}

	public SlopeOne getSlopeOne() {
		return slopeOne;
	}

	public void setSlopeOne(SlopeOne slopeOne) {
		this.slopeOne = slopeOne;
	}

	public List<EventoModel> getEventosRecomendados() {
		return eventosRecomendados;
	}

	public void setEventosRecomendados(List<EventoModel> eventosRecomendados) {
		this.eventosRecomendados = eventosRecomendados;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getPatharquivoavaliacoeseventos() {
		return pathArquivoAvaliacoesEventos;
	}

	public static String getPatharquivodiffeventos() {
		return pathArquivoDiffEventos;
	}
}
