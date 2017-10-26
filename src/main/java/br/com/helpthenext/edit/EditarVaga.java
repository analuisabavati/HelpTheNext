package br.com.helpthenext.edit;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.repository.AvaliacaoVagaRepository;
import br.com.helpthenext.repository.VagaRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.uteis.Uteis;

@SessionScoped
@Named(value = "editarVaga")
public class EditarVaga implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Inject
	transient private VagaRepository vagaRepository;
	
	@Inject
	transient private AvaliacaoVagaRepository avaliacaoVagaRepository;
	
	@Inject
	transient VoluntarioRepository voluntarioRepository;
	
	private VagaModel vaga;
	
	private UploadedFile uploadedFile;
	
	public void avaliarVaga() {
		VoluntarioEntity voluntarioByUsuarioSessao = voluntarioRepository.findVoluntarioByUsuarioSessao();

		vaga.getAvaliacaoVaga().setIdVaga(vaga.getId());
		vaga.getAvaliacaoVaga().setIdVoluntario(voluntarioByUsuarioSessao.getId());
		
		avaliacaoVagaRepository.salvarAtualizarAvaliacaoVaga(vaga.getAvaliacaoVaga());
		Uteis.MensagemInfo("Vaga avaliada!");
		vaga.getAvaliacaoVaga().setAvaliacao(0);
	}
	
	public String editarVaga() {
		if (uploadedFile != null) {
			vaga.setBanner(uploadedFile.getContents());
		}
		vagaRepository.atualizaVaga(vaga);
		Uteis.MensagemInfo("Vaga atualizada com sucesso!");
		
		return "home.xhtml";
	}

	public String removerVaga() {
		vagaRepository.removeVaga(vaga);
		Uteis.Mensagem("Vaga removida com sucesso!");
	
		
		return "home.xhtml";
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
