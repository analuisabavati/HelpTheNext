package br.com.helpthenext.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.EventoModel;
import br.com.helpthenext.repository.EventoRepository;
import br.com.helpthenext.uteis.Uteis;

@RequestScoped
@Named(value = "eventoController")
public class EventoController {
	
	@Inject
	EventoModel eventoModel;
	
	@Inject
	EventoRepository eventoRepository;
		
	public void salvarNovoEvento() {
		eventoRepository.salvarNovoRegistro(this.eventoModel);
		this.eventoModel = null;
		Uteis.MensagemInfo("Evento cadastrado com sucesso!");
	}
 
	private String[] selectedConsoles;

	  
    public String[] getSelectedConsoles() {
        return selectedConsoles;
    }
 
    public void setSelectedConsoles(String[] selectedConsoles) {
        this.selectedConsoles = selectedConsoles;
    }

	public EventoModel getEventoModel() {
		return eventoModel;
	}

	public void setEventoModel(EventoModel eventoModel) {
		this.eventoModel = eventoModel;
	}
    
    
}
