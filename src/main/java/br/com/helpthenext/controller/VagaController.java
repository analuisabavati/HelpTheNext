package br.com.helpthenext.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.recomendacoes.RecomendarVoluntarios;
import br.com.helpthenext.repository.VagaRepository;
import br.com.helpthenext.uteis.Uteis;

@Named(value = "vagaController")
@RequestScoped
public class VagaController {
	
	@Inject
	VagaModel vagaModel;

	@Inject
	VagaRepository vagaRepository;
	
	@Inject
	RecomendarVoluntarios recomendarVoluntarios;
	
	private UploadedFile uploadedFile;

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
		
	public String salvarNovaVaga() {
		if (uploadedFile != null) {
			vagaModel.setBanner(uploadedFile.getContents());
		}
		
		vagaRepository.salvarNovoRegistro(this.vagaModel);
		Uteis.MensagemInfo("Vaga cadastrada com sucesso!");
		
		recomendarVoluntarios.recomendarVoluntarios(vagaModel);
		
		return "recomendarVoluntario.xhtml";
	}

	public VagaModel getVagaModel() {
		return vagaModel;
	}

	public void setVagaModel(VagaModel vagaModel) {
		this.vagaModel = vagaModel;
	}

}
