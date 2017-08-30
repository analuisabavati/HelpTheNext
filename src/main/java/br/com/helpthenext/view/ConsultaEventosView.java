package br.com.helpthenext.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.EventoModel;
import br.com.helpthenext.repository.AvaliacaoEventoRepository;
import br.com.helpthenext.repository.EventoRepository;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.uteis.Uteis;

@ViewScoped
@Named(value = "consultaEventosView")
public class ConsultaEventosView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient EventoModel eventoModel;

	@Inject
	transient EventoRepository eventoRepository;

	@Inject
	transient ONGRepository ongRepository;

	@Inject
	transient VoluntarioRepository voluntarioRepository;
	
	@Inject
	transient AvaliacaoEventoRepository avaliacaoEventoRepository;

	@Produces
	private List<EventoModel> eventos;

	private EventoModel selectedEvento;
	
	private Integer avaliacao;

	private boolean botaoCurtir;

	private boolean botaoEditar;

	@PostConstruct // executado na inicialização da classe
	public void init() {
		this.eventos = eventoRepository.getEventos();
	}

	public void ativarBotoes() {
		ONGEntity ong = ongRepository.getONGByUsuarioSessao();
		if (ong != null && selectedEvento != null && selectedEvento.getOngEntity() != null
				&& selectedEvento.getOngEntity().getId() != null) {
			if (selectedEvento.getOngEntity() != null && selectedEvento.getOngEntity().getId().equals(ong.getId())) {
				botaoEditar = true;
			} else {
				botaoEditar = false;
			}
		}

		VoluntarioEntity vol = voluntarioRepository.getVoluntarioByUsuarioSessao();
		if (vol != null && selectedEvento != null && selectedEvento.getVoluntarioEntity() != null
				&& selectedEvento.getVoluntarioEntity().getId() != null) {
			if (selectedEvento.getVoluntarioEntity() != null
					&& selectedEvento.getVoluntarioEntity().getId().equals(vol.getId())) {
				botaoEditar = true;
			} else {
				botaoEditar = false;
			}
		}
	}

	public void curtirEvento() {
		avaliacaoEventoRepository.salvarAtualizarAvaliacaoEvento(selectedEvento.getAvaliacaoEvento());
		Uteis.MensagemInfo("Evento curtido!");
	}
	
	public boolean isBotaoCurtir() {
		return botaoCurtir;
	}

	public void setBotaoCurtir(boolean botaoCurtir) {
		this.botaoCurtir = botaoCurtir;
	}

	public boolean isBotaoEditar() {
		return botaoEditar;
	}

	public void setBotaoEditar(boolean botaoEditar) {
		this.botaoEditar = botaoEditar;
	}

	public EventoModel getEventoModel() {
		return eventoModel;
	}

	public void setEventoModel(EventoModel eventoModel) {
		this.eventoModel = eventoModel;
	}

	public List<EventoModel> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoModel> eventos) {
		this.eventos = eventos;
	}

	public EventoModel getSelectedEvento() {
		return selectedEvento;
	}

	public void setSelectedEvento(EventoModel selectedEvento) {
		this.selectedEvento = selectedEvento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}

}
