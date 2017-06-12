package br.com.helpthenext.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.repository.VagaRepository;
import br.com.helpthenext.uteis.Uteis;

@Named(value = "vagaController")
@RequestScoped
public class VagaController {
	
	@Inject
	VagaModel vagaModel;

	@Inject
	VagaRepository vagaRepository;
	
	List<VagaModel> vagas;
	
	public void salvarNovaVaga() {
		vagaRepository.salvarNovoRegistro(this.vagaModel);
		this.vagaModel = null;
		Uteis.MensagemInfo(" Vaga cadastrada com sucesso!");
	}

	public VagaModel getVagaModel() {
		return vagaModel;
	}

	public void setVagaModel(VagaModel vagaModel) {
		this.vagaModel = vagaModel;
	}
	
	private String[] selectedConsoles;

	  
    public String[] getSelectedConsoles() {
        return selectedConsoles;
    }
 
    public void setSelectedConsoles(String[] selectedConsoles) {
        this.selectedConsoles = selectedConsoles;
    }
    
    /***
	 * CARREGA AS PESSOAS NA INICIALIZAÇÃO 
	 */
	@PostConstruct
	public void init(){
 
		//RETORNAR AS PESSOAS CADASTRADAS
		this.vagas = vagaRepository.getVagas();
	}
 
	
}
