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
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.uteis.Uteis;

@ViewScoped
@Named(value = "consultaDoacoesView")
public class ConsultaDoacoesView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient DoacaoModel doacaoModel;

	@Inject
	transient DoacaoRepository doacaoRepository;

	@Inject
	transient VoluntarioRepository voluntarioRepository;

	@Inject
	UsuarioController usuarioController;

	@Produces
	private List<DoacaoModel> doacoes;

	private DoacaoModel selectedDoacao;

	private boolean botaoEditar;

	@PostConstruct
	public void init() {
		this.doacoes = doacaoRepository.getDoacaos();
	}

	public void ativarBotoes() {
		VoluntarioEntity vol = voluntarioRepository.getVoluntarioByUsuarioSessao();
		if (vol != null && selectedDoacao != null && selectedDoacao.getVoluntarioEntity() != null
				&& selectedDoacao.getVoluntarioEntity().getId() != null) {
			if (selectedDoacao.getVoluntarioEntity().getId().equals(vol.getId())) {
				botaoEditar = true;
			} else {
				botaoEditar = false;
			}
		}
	}

	public void editarDoacao() {
		doacaoRepository.ataulizarDoacao(selectedDoacao);
		Uteis.MensagemInfo("Doacao atualizada com sucesso!");
	}

	public void removerDoacao() {
		doacaoRepository.removerDoacao(selectedDoacao);
		Uteis.MensagemInfo("Doacao removida com sucesso!");
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
