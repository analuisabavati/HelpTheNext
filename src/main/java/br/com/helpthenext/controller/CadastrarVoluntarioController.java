package br.com.helpthenext.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.uteis.Uteis;

@Named(value = "cadastrarVoluntarioController")
@RequestScoped
public class CadastrarVoluntarioController {

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

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void salvarNovoVoluntario() {

		voluntarioModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		// INFORMANDO QUE O CADASTRO FOI VIA INPUT
		voluntarioModel.setOrigemCadastro("I");

		voluntarioRepository.SalvarNovoRegistro(this.voluntarioModel);

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