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
import br.com.helpthenext.uteis.Uteis;

@SessionScoped
@Named(value = "editarONG")
public class EditarONG implements Serializable {

	private static final long serialVersionUID = 22L;	
	
	@Inject
	transient ONGRepository ongRepository;
	
	ONGModel ong;
	
	private UploadedFile uploadedFile;

	@PostConstruct // executado na inicialização da classe
	public void init() {
		ONGEntity v = ongRepository.getONGByUsuarioSessao();
		this.ong = ongRepository.toONGModel(v);
	}
	
	public void atualizarONG() {	
		if (uploadedFile != null) {
			ong.setFoto(uploadedFile.getContents());
		}
		ongRepository.atualizarONG(this.ong);	
		Uteis.Mensagem("ONG atualizada com sucesso!");
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
