package br.com.helpthenext.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.DoacaoModel;
import br.com.helpthenext.repository.DoacaoRepository;
import br.com.helpthenext.uteis.Uteis;

@Named(value = "doacaoController")
@RequestScoped
public class DoacaoController {

	@Inject
	DoacaoModel doacaoModel;
	
	@Inject
	DoacaoRepository doacaoRepository;
		
	public void salvarNovoEvento() {
		doacaoRepository.salvarNovoRegistro(this.doacaoModel);
		this.doacaoModel = null;
		Uteis.MensagemInfo("Doa��o cadastrada com sucesso!");
	}
 
	private String[] selectedConsoles;
  
    public String[] getSelectedConsoles() {
        return selectedConsoles;
    }
 
    public void setSelectedConsoles(String[] selectedConsoles) {
        this.selectedConsoles = selectedConsoles;
    }

	public DoacaoModel getDoacaoModel() {
		return doacaoModel;
	}

	public void setDoacaoModel(DoacaoModel eventoModel) {
		this.doacaoModel = eventoModel;
	}
    
}
