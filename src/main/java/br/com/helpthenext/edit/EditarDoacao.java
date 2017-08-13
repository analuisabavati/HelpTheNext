package br.com.helpthenext.edit;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.DoacaoModel;
import br.com.helpthenext.repository.DoacaoRepository;
import br.com.helpthenext.uteis.Uteis;

@SessionScoped
@Named(value = "editarDoacao")
public class EditarDoacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient DoacaoRepository doacaoRepository;

	private DoacaoModel doacao;
	
	public void editarDoacao() {
		doacaoRepository.ataulizarDoacao(doacao);
		Uteis.MensagemInfo("Doacao atualizada com sucesso!");
	}

	public void removerDoacao() {
		doacaoRepository.removerDoacao(doacao);
		Uteis.MensagemInfo("Doacao removida com sucesso!");
	}

	public DoacaoRepository getDoacaoRepository() {
		return doacaoRepository;
	}

	public void setDoacaoRepository(DoacaoRepository doacaoRepository) {
		this.doacaoRepository = doacaoRepository;
	}

	public DoacaoModel getDoacao() {
		return doacao;
	}

	public void setDoacao(DoacaoModel doacao) {
		this.doacao = doacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
