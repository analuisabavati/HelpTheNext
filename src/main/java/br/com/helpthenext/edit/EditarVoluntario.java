package br.com.helpthenext.edit;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.uteis.Uteis;

@SessionScoped
@Named(value = "editarVoluntario")
public class EditarVoluntario implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Inject
	transient VoluntarioRepository voluntarioRepository;
	
	VoluntarioModel voluntario;
	
	private UploadedFile uploadedFile;
	
	@PostConstruct // executado na inicialização da classe
	public void init() {
		VoluntarioEntity v = voluntarioRepository.findVoluntarioByUsuarioSessao();
		this.voluntario = voluntarioRepository.toVoluntarioModel(v);	
	}
	
	public void atualizarVoluntario() {	
		if (uploadedFile != null) {
			voluntario.setFotoPerfil(uploadedFile.getContents());
		}
		voluntarioRepository.atualizarVoluntario(this.voluntario);	
		Uteis.Mensagem("Voluntario atualizado com sucesso!");
	}
	
	public VoluntarioRepository getVoluntarioRepository() {
		return voluntarioRepository;
	}

	public void setVoluntarioRepository(VoluntarioRepository voluntarioRepository) {
		this.voluntarioRepository = voluntarioRepository;
	}

	public VoluntarioModel getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(VoluntarioModel voluntario) {
		this.voluntario = voluntario;
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
