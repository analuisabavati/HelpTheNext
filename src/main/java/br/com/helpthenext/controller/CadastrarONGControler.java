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
 
}
