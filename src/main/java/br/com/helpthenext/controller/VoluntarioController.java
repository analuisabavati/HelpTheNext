package br.com.helpthenext.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.helpthenext.enums.TipoUsuario;
import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.UsuarioRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.uteis.Uteis;

@RequestScoped
@Named(value = "voluntarioController")
public class VoluntarioController {

	@Inject
	VoluntarioModel voluntarioModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	VoluntarioRepository voluntarioRepository;

	@Inject
	UsuarioRepository usuarioRepository;
	
	private UploadedFile uploadedFile;

	public void salvarNovoVoluntario() {
		if (usuarioRepository.validaUsuarioCadastrado(this.voluntarioModel.getUsuarioEntity().getUsuario()) != null) {
			Uteis.Mensagem("Nome de usuario já utilizado. Por favor informe outro usuario!");
		} else {
			if (uploadedFile != null) {
				voluntarioModel.setFotoPerfil(uploadedFile.getContents());
			}
			voluntarioModel.getUsuarioEntity().setTipoUsuario(TipoUsuario.VOLUNTARIO);
			voluntarioRepository.salvarNovoRegistro(this.voluntarioModel);
			this.voluntarioModel = null;
			Uteis.MensagemInfo(" Voluntario cadastrado com sucesso!");
		}
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public VoluntarioModel getVoluntarioModel() {
		return voluntarioModel;
	}

	public void setVoluntarioModel(VoluntarioModel voluntarioModel) {
		this.voluntarioModel = voluntarioModel;
	}
	
}