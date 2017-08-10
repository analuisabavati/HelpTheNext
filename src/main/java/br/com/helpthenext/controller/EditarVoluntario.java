package br.com.helpthenext.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.VoluntarioEntity;

@SessionScoped
@Named(value = "editarVoluntario")
public class EditarVoluntario implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Inject
	transient VoluntarioRepository voluntarioRepository;
	
	VoluntarioModel voluntario;
	
	@PostConstruct // executado na inicialização da classe
	public void init() {
		VoluntarioEntity v = voluntarioRepository.getVoluntarioByUsuarioSessao();
		this.voluntario = voluntarioRepository.toVoluntarioModel(v);
	}
	
	public void atualizarVoluntario() {	
		voluntarioRepository.atualizarVoluntario(this.voluntario);	
	}
	
	public VoluntarioRepository getVoluntarioRepository() {
		return voluntarioRepository;
	}

	public void setVoluntarioRepository(VoluntarioRepository voluntarioRepository) {
		this.voluntarioRepository = voluntarioRepository;
	}

	public VoluntarioModel getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(VoluntarioModel voluntario) {
		this.voluntario = voluntario;
	}

	
}
