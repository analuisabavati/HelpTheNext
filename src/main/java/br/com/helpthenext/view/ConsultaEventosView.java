package br.com.helpthenext.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.controller.UsuarioController;
import br.com.helpthenext.enums.TipoUsuario;
import br.com.helpthenext.model.EventoModel;
import br.com.helpthenext.model.UsuarioModel;
import br.com.helpthenext.repository.EventoRepository;

@ViewScoped
@Named(value="consultaEventosView")
public class ConsultaEventosView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject transient
	EventoModel eventoModel;
	
	@Inject transient
	EventoRepository eventoRepository;
	
	@Inject
	UsuarioController usuarioController;
	
	@Produces 
	private List<EventoModel> eventos;
	
	private EventoModel selectedEvento;
	
	private boolean botaoCurtir;

	private boolean botaoEditar;
	
	@PostConstruct // executado na inicialização da classe
	public void init(){
		this.eventos = eventoRepository.getEventos();
	}
	
	public void ativarBotoes() {
		
		UsuarioModel usuario = usuarioController.GetUsuarioSession();
		
		if (usuario != null && usuario.getTipoUsuario().equals(TipoUsuario.VOLUNTARIO)) {
			botaoCurtir = true;
		} else {
			botaoCurtir = false;
		}
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

}
