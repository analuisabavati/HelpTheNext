package br.com.helpthenext.edit;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.helpthenext.model.DoacaoModel;
import br.com.helpthenext.repository.DoacaoRepository;
import br.com.helpthenext.uteis.Uteis;

@SessionScoped
@Named(value = "editarDoacao")
public class EditarDoacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient DoacaoRepository doacaoRepository;

	private DoacaoModel doacao;
	
	private UploadedFile uploadedFile;
	
	public void editarDoacao() {
		if (uploadedFile != null) {
			doacao.setFoto(uploadedFile.getContents());
		}
		doacaoRepository.atualizarDoacao(doacao);
		Uteis.Mensagem("Doacao atualizada com sucesso!");
	}

	public String removerDoacao() {
		doacaoRepository.removerDoacao(doacao);
		Uteis.Mensagem("Doacao removida com sucesso!");
		
		return "home.xhtml";
	}

	public DoacaoRepository getDoacaoRepository() {
		return doacaoRepository;
	}

	public void setDoacaoRepository(DoacaoRepository doacaoRepository) {
		this.doacaoRepository = doacaoRepository;
	}

	public DoacaoModel getDoacao() {
		return doacao;
	}

	public void setDoacao(DoacaoModel doacao) {
		this.doacao = doacao;
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
