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

import br.com.helpthenext.repository.VagaRepository;
import br.com.helpthenext.repository.entity.VagaEntity;

@Named
@ApplicationScoped
public class VagaImagem {

	@Inject
	VagaRepository vagaRepository;
	
	public StreamedContent getImagem() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        else { 
            String vagaId = context.getExternalContext().getRequestParameterMap().get("vagaId");
            if (vagaId != null) {
            	VagaEntity vaga = vagaRepository.getVaga(new Long(vagaId));
            	if (vaga != null && vaga.getBanner() != null ) {
            		return new DefaultStreamedContent(new ByteArrayInputStream(vaga.getBanner()));
            	}
            	
            }
        }
        return null;
    }
}
