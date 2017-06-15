package br.com.helpthenext.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.enums.TipoUsuario;
import br.com.helpthenext.model.ONGModel;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.UsuarioRepository;
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
	
	public void salvarNovaONG() {
		if (usuarioRepository.validaUsuarioCadastrado(this.ongModel.getUsuarioEntity().getUsuario()) != null) {
			Uteis.Mensagem("Nome de usuario já utilizado. Por favor informe outro usuario!");
		} else {
			ongModel.getUsuarioEntity().setTipoUsuario(TipoUsuario.ONG);
			ongRepository.salvarNovoRegistro(this.ongModel);
			this.ongModel = null;
			Uteis.MensagemInfo("ONG cadastrada com sucesso!");
		}
	}

	private String[] selectedConsoles;

	public String[] getSelectedConsoles() {
		return selectedConsoles;
	}

	public void setSelectedConsoles(String[] selectedConsoles) {
		this.selectedConsoles = selectedConsoles;
	}

	public ONGModel getOngModel() {
		return ongModel;
	}

	public void setOngModel(ONGModel ongModel) {
		this.ongModel = ongModel;
	}
	
}
