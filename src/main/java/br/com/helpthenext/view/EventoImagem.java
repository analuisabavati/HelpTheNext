package br.com.helpthenext.view;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.helpthenext.repository.EventoRepository;
import br.com.helpthenext.repository.entity.EventoEntity;

@Named
@ApplicationScoped
public class EventoImagem {

	@Inject
	EventoRepository eventoRepository;
	
	public StreamedContent getImagem() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        else {
            String eventoId = context.getExternalContext().getRequestParameterMap().get("eventoId");
            if (eventoId != null) {
            	EventoEntity evento = eventoRepository.findEventoById(new Long(eventoId));
            	if (evento != null && evento.getBanner() != null ) {
            		return new DefaultStreamedContent(new ByteArrayInputStream(evento.getBanner()));
            	}
            }
        }
        return null;
    }
}
