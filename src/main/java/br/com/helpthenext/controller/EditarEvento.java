package br.com.helpthenext.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.EventoModel;
import br.com.helpthenext.repository.EventoRepository;
import br.com.helpthenext.uteis.Uteis;

@ViewScoped
@Named(value = "editarEvento")
public class EditarEvento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	transient EventoRepository eventoRepository;
	
	private EventoModel evento;

	public void editarVaga() {
		eventoRepository.atualizarEvento(evento);
		Uteis.MensagemInfo("Vaga atualizada com sucesso!");
	}

	public void removerVaga() {
		eventoRepository.removeEvento(evento);
		Uteis.MensagemInfo("Vaga removida com sucesso!");
	}

	public EventoRepository getEventoRepository() {
		return eventoRepository;
	}

	public void setEventoRepository(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}

	public EventoModel getEvento() {
		return evento;
	}

	public void setEvento(EventoModel evento) {
		this.evento = evento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
