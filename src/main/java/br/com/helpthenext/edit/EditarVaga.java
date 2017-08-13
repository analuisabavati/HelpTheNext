package br.com.helpthenext.edit;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.repository.VagaRepository;
import br.com.helpthenext.uteis.Uteis;

@SessionScoped
@Named(value = "editarVaga")
public class EditarVaga implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Inject
	transient private VagaRepository vagaRepository;
	
	private VagaModel vaga;
	
	public void editarVaga() {
		vagaRepository.atualizaVaga(vaga);
		Uteis.MensagemInfo("Vaga atualizada com sucesso!");
	}

	public void removerVaga() {
		vagaRepository.removeVaga(vaga);
		Uteis.MensagemInfo("Vaga removida com sucesso!");
	}

	public VagaRepository getVagaRepository() {
		return vagaRepository;
	}

	public void setVagaRepository(VagaRepository vagaRepository) {
		this.vagaRepository = vagaRepository;
	}

	public VagaModel getVaga() {
		return vaga;
	}

	public void setVaga(VagaModel vaga) {
		this.vaga = vaga;
	}
}
