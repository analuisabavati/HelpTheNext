package br.com.helpthenext.view;

import java.util.List;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.repository.VagaRepository;
 
@Named(value="consultaVagasView")
@ViewScoped
public class ConsultaVagasView implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	@Inject transient
	private VagaModel vagaModel;
 
	@Produces 
	private List<VagaModel> vagas;
 
	@Inject transient
	private VagaRepository vagaRepository;
	
	private VagaModel selectedVaga;
 
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
	/***
	 * CARREGA AS PESSOAS NA INICIALIZAÇÃO 
	 */
	@PostConstruct
	public void init(){
 
		//RETORNAR AS PESSOAS CADASTRADAS
		this.vagas = vagaRepository.getVagas();
	}
}
