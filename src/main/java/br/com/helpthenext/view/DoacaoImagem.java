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

import br.com.helpthenext.repository.DoacaoRepository;
import br.com.helpthenext.repository.entity.DoacaoEntity;

@Named
@ApplicationScoped
public class DoacaoImagem {

	@Inject
	DoacaoRepository DoacaoRepository;
	
	public StreamedContent getImagem() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        else {
            String doaocaoId = context.getExternalContext().getRequestParameterMap().get("doacaoId");
            if (doaocaoId != null) {
            	DoacaoEntity doaocao = DoacaoRepository.getDoacao(new Long(doaocaoId));
            	if (doaocao != null && doaocao.getFoto() != null ) {
            		return new DefaultStreamedContent(new ByteArrayInputStream(doaocao.getFoto()));
            	}
            }
        }
        return null;
    }
}
