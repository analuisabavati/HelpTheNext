package br.com.helpthenext.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

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
	
	private UploadedFile uploadedFile;

	public void salvarNovaDoacao() {
		if (uploadedFile != null) {
			doacaoModel.setFoto(uploadedFile.getContents());
		}
		doacaoRepository.salvarNovoRegistro(this.doacaoModel);
		this.doacaoModel = null;
		Uteis.MensagemInfo("Doação cadastrada com sucesso!");
	}
 
	public DoacaoModel getDoacaoModel() {
		return doacaoModel;
	}

	public void setDoacaoModel(DoacaoModel eventoModel) {
		this.doacaoModel = eventoModel;
	}
    
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
		
}
