package br.com.helpthenext.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.EventoModel;
import br.com.helpthenext.repository.EventoRepository;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.uteis.TituloUteis;

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
	
	@Produces
	private List<EventoModel> eventos;

	private EventoModel selectedEvento;
	
	private Integer avaliacao;

	private boolean botaoCurtir;
	private Boolean exibirBotaoEditar;
	
	private String busca;
	private String causa;

	@PostConstruct // executado na inicialização da classe
	public void init() {
		this.eventos = eventoRepository.findAll();
	}

	public void ativarBotoes() {
		ONGEntity ong = ongRepository.findONGByUsuarioSessao();
		if (ong != null && selectedEvento != null && selectedEvento.getOngEntity() != null
				&& selectedEvento.getOngEntity().getId() != null) {
			if (selectedEvento.getOngEntity() != null && selectedEvento.getOngEntity().getId().equals(ong.getId())) {
				exibirBotaoEditar = true;
			} else {
				exibirBotaoEditar = null;
			}
		}

		VoluntarioEntity vol = voluntarioRepository.findVoluntarioByUsuarioSessao();
		if (vol != null && selectedEvento != null && selectedEvento.getVoluntarioEntity() != null
				&& selectedEvento.getVoluntarioEntity().getId() != null) {
			if (selectedEvento.getVoluntarioEntity() != null
					&& selectedEvento.getVoluntarioEntity().getId().equals(vol.getId())) {
				exibirBotaoEditar = true;
			} else {
				exibirBotaoEditar = null;
			}
		}
	}
	
	public void retornaEventoConformeBusca() {
		
		if (busca == null || busca.isEmpty()) {
			eventos = eventoRepository.findAll();
		}
		
		if (eventos == null || eventos.isEmpty()) {
			eventos = eventoRepository.findAll();
		}
		
		List<EventoModel> eventoConformeBusca = new ArrayList<>();
		for (EventoModel eventoModel : this.eventos) {
			if (TituloUteis.isSemelhantes(busca, eventoModel.getTitulo())) {
				eventoConformeBusca.add(eventoModel);
			}
		}
		eventos = eventoConformeBusca;
	}
	
	public void filtrar() {
		if (causa == null || causa.isEmpty()) {
			eventos = eventoRepository.findAll();
			return;
		}
		
		eventos = eventoRepository.findAll();
		
		List<EventoModel> eventosFiltrados = new ArrayList<>();
		for (EventoModel evento : eventos) {
			if (evento.getCausa() != null && 
					evento.getCausa().equals(causa)) {
				eventosFiltrados.add(evento);
			}
		}
		
		eventos = eventosFiltrados;
	}
	
	public boolean isBotaoCurtir() {
		return botaoCurtir;
	}

	public void setBotaoCurtir(boolean botaoCurtir) {
		this.botaoCurtir = botaoCurtir;
	}

	public Boolean getExibirBotaoEditar() {
		return exibirBotaoEditar;
	}

	public void setExibirBotaoEditar(Boolean exibirBotaoEditar) {
		this.exibirBotaoEditar = exibirBotaoEditar;
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

	public EventoRepository getEventoRepository() {
		return eventoRepository;
	}

	public void setEventoRepository(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}

	public ONGRepository getOngRepository() {
		return ongRepository;
	}

	public void setOngRepository(ONGRepository ongRepository) {
		this.ongRepository = ongRepository;
	}

	public VoluntarioRepository getVoluntarioRepository() {
		return voluntarioRepository;
	}

	public void setVoluntarioRepository(VoluntarioRepository voluntarioRepository) {
		this.voluntarioRepository = voluntarioRepository;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

}
