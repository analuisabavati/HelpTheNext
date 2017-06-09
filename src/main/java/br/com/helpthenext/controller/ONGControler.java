package br.com.helpthenext.controller;

import javax.inject.Inject;

import br.com.helpthenext.model.ONGModel;
import br.com.helpthenext.repository.ONGRepository;

public class ONGControler {

	@Inject
	ONGModel Model;
 
	@Inject
	UsuarioController usuarioController;
 
	@Inject
	ONGRepository ongRepository;
 
}
