package br.com.helpthenext.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.enums.TipoUsuario;
import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.uteis.Uteis;

@Named(value = "voluntarioController")
@RequestScoped
public class VoluntarioController {

	@Inject
	VoluntarioModel voluntarioModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	VoluntarioRepository voluntarioRepository;

	public VoluntarioModel getVoluntarioModel() {
		return voluntarioModel;
	}

	public void setVoluntarioModel(VoluntarioModel voluntarioModel) {
		this.voluntarioModel = voluntarioModel;
	}


	public void salvarNovoVoluntario() {
		voluntarioModel.getUsuarioEntity().setTipoUsuario(TipoUsuario.VOLUNTARIO);		
		voluntarioRepository.salvarNovoRegistro(this.voluntarioModel);
		this.voluntarioModel = null;
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
	}

	private String[] selectedConsoles;

  
    public String[] getSelectedConsoles() {
        return selectedConsoles;
    }
 
    public void setSelectedConsoles(String[] selectedConsoles) {
        this.selectedConsoles = selectedConsoles;
    }
 
}