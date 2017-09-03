package br.com.helpthenext.edit;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.repository.AvaliacaoVagaRepository;
import br.com.helpthenext.repository.VagaRepository;
import br.com.helpthenext.uteis.Uteis;

@SessionScoped
@Named(value = "editarVaga")
public class EditarVaga implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Inject
	transient private VagaRepository vagaRepository;
	
	@Inject
	transient private AvaliacaoVagaRepository avaliacaoVagaRepository;
	
	private VagaModel vaga;
	
	private UploadedFile uploadedFile;
	
	public void avaliarVaga() {
		avaliacaoVagaRepository.salvarAtualizarAvaliacaoVaga(vaga.getAvaliacaoVaga());
		Uteis.MensagemInfo("Vaga avaliada!");
		vaga.getAvaliacaoVaga().setAvaliacao(0);
	}
	
	public void editarVaga() {
		if (uploadedFile != null) {
			vaga.setBanner(uploadedFile.getContents());
		}
		vagaRepository.atualizaVaga(vaga);
		Uteis.Mensagem("Vaga atualizada com sucesso!");
	}

	public void removerVaga() {
		vagaRepository.removeVaga(vaga);
		Uteis.Mensagem("Vaga removida com sucesso!");
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

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
