package br.com.helpthenext.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.helpthenext.enums.TipoUsuario;
import br.com.helpthenext.model.ONGModel;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.UsuarioRepository;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.uteis.Uteis;

@RequestScoped
@Named(value = "ongController")
public class ONGController {

	@Inject
	ONGModel ongModel;
 
	@Inject
	UsuarioController usuarioController;
 
	@Inject
	ONGRepository ongRepository;
	
	@Inject
	UsuarioRepository usuarioRepository;
	
	@Inject
	ONGEntity ong;
	
	private UploadedFile uploadedFile;
	
	@PostConstruct // executado na inicialização da classe
	public void init() {
		this.ong = ongRepository.findONGByUsuarioSessao();
	}
	
	public void atualizarONG() {	
		ongRepository.atualizarONG(ong);
	}
	
	public String salvarNovaONG() {
		if (usuarioRepository.validaUsuarioCadastrado(this.ongModel.getUsuarioEntity().getUsuario()) != null) {
			Uteis.Mensagem("Nome de usuario já utilizado. Por favor informe outro usuario!");
		} else {
			if (uploadedFile != null) {
				ongModel.setFoto(uploadedFile.getContents());
			}
			ongModel.getUsuarioEntity().setTipoUsuario(TipoUsuario.ONG);
			ongRepository.salvarNovoRegistro(this.ongModel);
			this.ongModel = null;
			Uteis.MensagemInfo("ONG cadastrada com sucesso!");
		}
		
		return "index.xhtml";
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public ONGModel getOngModel() {
		return ongModel;
	}

	public void setOngModel(ONGModel ongModel) {
		this.ongModel = ongModel;
	}
	
}
