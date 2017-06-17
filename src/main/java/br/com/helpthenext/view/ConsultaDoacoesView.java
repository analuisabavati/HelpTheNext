package br.com.helpthenext.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.controller.UsuarioController;
import br.com.helpthenext.model.DoacaoModel;
import br.com.helpthenext.repository.DoacaoRepository;

@ViewScoped
@Named(value="consultaDoacoesView")
public class ConsultaDoacoesView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject transient
	DoacaoModel doacaoModel;
	
	@Inject transient
	DoacaoRepository doacaoRepository;
	
	@Inject
	UsuarioController usuarioController;
	
	@Produces 
	private List<DoacaoModel> doacoes;
	
	private DoacaoModel selectedDoacao;
	
	private boolean botaoEditar;
	
	@PostConstruct 
	public void init(){
		this.doacoes = doacaoRepository.getDoacaos();
	}
	
	public void ativarBotoes() {
	
	}

	public DoacaoModel getDoacaoModel() {
		return doacaoModel;
	}

	public void setDoacaoModel(DoacaoModel doacaoModel) {
		this.doacaoModel = doacaoModel;
	}

	public List<DoacaoModel> getDoacoes() {
		return doacoes;
	}

	public void setDoacoes(List<DoacaoModel> doacoes) {
		this.doacoes = doacoes;
	}

	public DoacaoModel getSelectedDoacao() {
		return selectedDoacao;
	}

	public void setSelectedDoacao(DoacaoModel selectedDoacao) {
		this.selectedDoacao = selectedDoacao;
	}

	public boolean isBotaoEditar() {
		return botaoEditar;
	}

	public void setBotaoEditar(boolean botaoEditar) {
		this.botaoEditar = botaoEditar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
