package br.com.helpthenext.edit;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.helpthenext.model.ONGModel;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.util.Uteis;

@SessionScoped
@Named(value = "editarONG")
public class EditarONG implements Serializable {

	private static final long serialVersionUID = 22L;	
	
	@Inject
	transient ONGRepository ongRepository;
	
	ONGModel ong;
	
	private UploadedFile uploadedFile;

	@PostConstruct // executado na inicializa��o da classe
	public void init() {
		ONGEntity v = ongRepository.findONGByUsuarioSessao();
		this.ong = ongRepository.toONGModel(v);
	}
	
	public String atualizarONG() {	
		if (uploadedFile != null) {
			ong.setFoto(uploadedFile.getContents());
		}
		ongRepository.atualizarONG(this.ong);	
		Uteis.MensagemInfo("ONG atualizada com sucesso!");
		
		return "home.xhtml";
	}
	
	
	public String removerONG() {	
	
		ongRepository.removerONG(this.ong);	
		Uteis.Mensagem("ONG removida com sucesso!");
		
		return "fim.xhtml";
	}
		

	public ONGRepository getOngRepository() {
		return ongRepository;
	}

	public void setOngRepository(ONGRepository ongRepository) {
		this.ongRepository = ongRepository;
	}

	public ONGModel getOng() {
		return ong;
	}

	public void setOng(ONGModel ong) {
		this.ong = ong;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
