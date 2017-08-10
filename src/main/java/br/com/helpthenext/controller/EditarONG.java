package br.com.helpthenext.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.ONGModel;
import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.repository.entity.VoluntarioEntity;

@SessionScoped
@Named(value = "editarONG")
public class EditarONG implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Inject
	transient ONGRepository ongRepository;
	
	ONGModel ong;

	@PostConstruct // executado na inicialização da classe
	public void init() {
		ONGEntity v = ongRepository.getONGByUsuarioSessao();
		this.ong = ongRepository.toONGModel(v);
	}

	public ONGRepository getOngRepository() {
		return ongRepository;
	}

	public void setOngRepository(ONGRepository ongRepository) {
		this.ongRepository = ongRepository;
	}

	public ONGModel getOng() {
		return ong;
	}

	public void setOng(ONGModel ong) {
		this.ong = ong;
	}

}
