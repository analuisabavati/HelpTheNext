package br.com.helpthenext.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

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
		
	private UploadedFile uploadedFile;
	
	public String salvarNovoEvento() {
		if (uploadedFile != null) {
			eventoModel.setBanner(uploadedFile.getContents());
		}
		eventoRepository.salvarNovoRegistro(this.eventoModel);
		this.eventoModel = null;
		Uteis.MensagemInfo("Evento cadastrado com sucesso!");
		
		return "home.xhtml";
	}
	
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
	
	public EventoRepository getEventoRepository() {
		return eventoRepository;
	}


	public void setEventoRepository(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public EventoModel getEventoModel() {
		return eventoModel;
	}

	public void setEventoModel(EventoModel eventoModel) {
		this.eventoModel = eventoModel;
	}
    
    
}
