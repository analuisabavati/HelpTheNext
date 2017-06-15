package br.com.helpthenext.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	
	@Inject
	FileUploadView fileUpload;
	
	private List<String> x = new ArrayList<>();
	
	public List<String> getX() {
		return x;
	}

	public void setX(List<String> x) {
		this.x = x;
	}

	public VoluntarioModel getVoluntarioModel() {
		return voluntarioModel;
	}

	public void setVoluntarioModel(VoluntarioModel voluntarioModel) {
		this.voluntarioModel = voluntarioModel;
	}

	public void salvarNovoVoluntario() {
		if (usuarioRepository.validaUsuarioCadastrado(this.voluntarioModel.getUsuarioEntity().getUsuario()) != null) {
			Uteis.Mensagem("Nome de usuario já utilizado. Por favor informe outro usuario!");
		} else {
			
			voluntarioModel.getUsuarioEntity().setTipoUsuario(TipoUsuario.VOLUNTARIO);
			voluntarioRepository.salvarNovoRegistro(this.voluntarioModel);
			this.voluntarioModel = null;
			Uteis.MensagemInfo(" Voluntario cadastrado com sucesso!");
		}
	}

	
}