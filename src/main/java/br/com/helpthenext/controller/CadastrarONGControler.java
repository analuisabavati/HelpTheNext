package br.com.helpthenext.controller;

import javax.inject.Inject;

import br.com.helpthenext.model.ONGModel;
import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.uteis.Uteis;

public class CadastrarONGControler {

	@Inject
	ONGModel Model;
 
	@Inject
	UsuarioController usuarioController;
 
	@Inject
	ONGRepository ongRepository;
 
 /*
	public VoluntarioModel getVoluntarioModel() {
		return voluntarioModel;
	}
 
	public void setVoluntarioModel(VoluntarioModel voluntarioModel) {
		this.voluntarioModel = voluntarioModel;
	}
 
	/**
	 *SALVA UM NOVO REGISTRO VIA INPUT 
	 */
	public void salvarNovoVoluntario(){
 
		voluntarioModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
 
		voluntarioRepository.SalvarNovoRegistro(this.voluntarioModel);
 
		this.voluntarioModel = null;
 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}*/
}
