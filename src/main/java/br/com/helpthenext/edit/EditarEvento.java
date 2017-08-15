package br.com.helpthenext.edit;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.helpthenext.model.EventoModel;
import br.com.helpthenext.repository.EventoRepository;
import br.com.helpthenext.uteis.Uteis;

@SessionScoped
@Named(value = "editarEvento")
public class EditarEvento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	transient EventoRepository eventoRepository;
	
	private EventoModel evento;
	
	private UploadedFile uploadedFile;

	public void editarEvento() {
		if (uploadedFile != null) {
			evento.setBanner(uploadedFile.getContents());
		}
		eventoRepository.atualizarEvento(evento);
		Uteis.MensagemInfo("Evento atualizado com sucesso!");
	}

	public void removerEvento() {
		eventoRepository.removeEvento(evento);
		Uteis.MensagemInfo("Evento removido com sucesso!");
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

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	
	
}
