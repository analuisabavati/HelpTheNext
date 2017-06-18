package br.com.helpthenext.view;

import java.util.List;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.enums.TipoUsuario;
import br.com.helpthenext.model.UsuarioModel;
import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.repository.VagaRepository;
 
@ViewScoped
@Named(value="consultaVagasView")
public class ConsultaVagasView implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	@Inject transient
	private VagaModel vagaModel;
	
	@Inject transient
	private VagaRepository vagaRepository;
 
	@Produces 
	private List<VagaModel> vagas;
 
	private VagaModel selectedVaga;
	
	private boolean botaoEditar = false;
	
	@PostConstruct // executado na inicialização da classe
	public void init(){
		this.vagas = vagaRepository.getVagas();
	}
	
	public void ativarBotoes() {
		
	}
 
	public List<VagaModel> getVagas() {
		return vagas;
	}
	public void setVagas(List<VagaModel> vagas) {
		this.vagas = vagas;
	}		
	public VagaModel getVagaModel() {
		return vagaModel;
	}
	public void setVagaModel(VagaModel vagaModel) {
		this.vagaModel = vagaModel;
	}
	
	public VagaModel getSelectedVaga() {
		return selectedVaga;
	}
	public void setSelectedVaga(VagaModel selectedVaga) {
		this.selectedVaga = selectedVaga;
	}

	public boolean isBotaoEditar() {
		return botaoEditar;
	}

	public void setBotaoEditar(boolean botaoEditar) {
		this.botaoEditar = botaoEditar;
	}
	
	

}
