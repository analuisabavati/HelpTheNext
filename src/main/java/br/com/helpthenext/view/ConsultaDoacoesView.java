package br.com.helpthenext.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.mail.JavaMailApp;
import br.com.helpthenext.model.DoacaoModel;
import br.com.helpthenext.repository.DoacaoRepository;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.repository.entity.VoluntarioEntity;

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
	transient JavaMailApp javaMailApp;

	@Inject
	transient ONGRepository ongRepository;

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
			if (selectedDoacao.getVoluntarioEntity() != null
					&& selectedDoacao.getVoluntarioEntity().getId().equals(vol.getId())) {
				botaoEditar = true;
			} else {
				botaoEditar = false;
			}
		}
	}

	public void enviarEmailInteresse() {

		ONGEntity ong = ongRepository.getONGByUsuarioSessao();

		StringBuilder msg = new StringBuilder();
		msg.append("--------------------------- HelpTheNext -------------------------");
		msg.append("\n");
		msg.append("\n");
		msg.append("\n");
		msg.append("Olá " + selectedDoacao.getVoluntarioEntity().getNome());
		msg.append("\n");
		msg.append("\n");
		msg.append("A ONG " + ong.getNomeONG() + " tem interesse na sua doação intitulada como "
				+ selectedDoacao.getTitulo() + ".");
		msg.append("\n");
		msg.append("\n");
		msg.append("Segue abaixo o contado da ONG:");
		msg.append("\n");
		msg.append("Telefone: " + ong.getTelefone());
		msg.append("\n");
		msg.append("Email: " + ong.getEmail());
		msg.append("\n");
		msg.append("\n");
		msg.append("--------------------------- HelpTheNext -------------------------");
		msg.append("\n");
		msg.append("\n");
		msg.append(
				"Por favor, não responda a este e-mail - que foi gerada a partir de uma conta que envia mensagens automaticamente e não pode receber respostas de volta.");

		String assunto = "[HelpTheNext] Uma ONG está interessada na sua doação!!";

		javaMailApp.enviarEmail(selectedDoacao.getVoluntarioEntity().getEmail(), msg.toString(), assunto);
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
