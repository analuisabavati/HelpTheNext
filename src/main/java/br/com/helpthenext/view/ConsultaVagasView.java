package br.com.helpthenext.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.VagaRepository;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.uteis.Uteis;

@ViewScoped
@Named(value = "consultaVagasView")
public class ConsultaVagasView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private VagaModel vagaModel;

	@Inject
	transient private VagaRepository vagaRepository;

	@Inject
	transient private ONGRepository ongRepository;

	@Produces
	private List<VagaModel> vagas;

	private VagaModel selectedVaga;

	private boolean botaoEditar;

	@PostConstruct // executado na inicialização da classe
	public void init() {
		this.vagas = null;
		this.vagas = vagaRepository.getVagas();
	}
	
	public void ativarBotoes() {
		ONGEntity ong = ongRepository.getONGByUsuarioSessao();
		if (ong != null && selectedVaga != null && selectedVaga.getOngEntity() != null
				&& selectedVaga.getOngEntity().getId() != null) {
			if (selectedVaga.getOngEntity().getId().equals(ong.getId())) {
				botaoEditar = true;
			} else {
				botaoEditar = false;
			}
		}
	}

	public void editarVaga() {
		vagaRepository.atualizaVaga(selectedVaga);
		Uteis.MensagemInfo("Vaga atualizada com sucesso!");
	}

	public void removerVaga() {
		vagaRepository.removeVaga(selectedVaga);
		Uteis.MensagemInfo("Vaga removida com sucesso!");
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
