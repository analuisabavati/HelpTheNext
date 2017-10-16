package br.com.helpthenext.view;

import java.io.Serializable;
import java.util.ArrayList;
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
import br.com.helpthenext.uteis.TituloUteis;

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
	
	private String busca;

	@PostConstruct
	public void init() {
		this.doacoes = doacaoRepository.findAll();
	}

	public void ativarBotoes() {
		VoluntarioEntity vol = voluntarioRepository.findVoluntarioByUsuarioSessao();
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
	
	public void retornaDoacoesConformeBusca() {

		if (doacoes == null || doacoes.isEmpty()) {
			doacoes = doacaoRepository.findAll();
		}

		List<DoacaoModel> doacoesConformeBusca = new ArrayList<>();
		for (DoacaoModel doacaoModel : this.doacoes) {
			if (TituloUteis.isSemelhantes(busca, doacaoModel.getTitulo())) {
				doacoesConformeBusca.add(doacaoModel);
			}
		}
		
		doacoes = doacoesConformeBusca;
	}

	public void enviarEmailInteresse() {

		ONGEntity ong = ongRepository.findONGByUsuarioSessao();

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

	public DoacaoRepository getDoacaoRepository() {
		return doacaoRepository;
	}

	public void setDoacaoRepository(DoacaoRepository doacaoRepository) {
		this.doacaoRepository = doacaoRepository;
	}

	public VoluntarioRepository getVoluntarioRepository() {
		return voluntarioRepository;
	}

	public void setVoluntarioRepository(VoluntarioRepository voluntarioRepository) {
		this.voluntarioRepository = voluntarioRepository;
	}

	public JavaMailApp getJavaMailApp() {
		return javaMailApp;
	}

	public void setJavaMailApp(JavaMailApp javaMailApp) {
		this.javaMailApp = javaMailApp;
	}

	public ONGRepository getOngRepository() {
		return ongRepository;
	}

	public void setOngRepository(ONGRepository ongRepository) {
		this.ongRepository = ongRepository;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}
	
}

