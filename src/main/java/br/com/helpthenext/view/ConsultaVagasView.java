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
import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.VagaRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.uteis.TituloUteis;
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

	@Inject
	transient JavaMailApp javaMailApp;

	@Inject
	transient VoluntarioRepository voluntarioRepository;

	@Produces
	private List<VagaModel> vagas;

	private VagaModel selectedVaga;

	private boolean botaoEditar;

	private String busca;

	@PostConstruct // executado na inicializa��o da classe
	public void init() {
		this.vagas = null;
		this.vagas = vagaRepository.findAll();
	}

	public void ativarBotoes() {
		ONGEntity ong = ongRepository.findONGByUsuarioSessao();
		if (ong != null && selectedVaga != null && selectedVaga.getOngEntity() != null
				&& selectedVaga.getOngEntity().getId() != null) {
			if (selectedVaga.getOngEntity() != null && selectedVaga.getOngEntity().getId().equals(ong.getId())) {
				botaoEditar = true;
			} else {
				botaoEditar = false;
			}
		}
	}

	public void editarVaga() {
		vagaRepository.atualizaVaga(selectedVaga);
		Uteis.Mensagem("Vaga atualizada com sucesso!");
		init();
	}

	public void removerVaga() {
		vagaRepository.removeVaga(selectedVaga);
		Uteis.Mensagem("Vaga removida com sucesso!");
		init();
	}

	public void enviarEmailInteresse() {

		VoluntarioEntity vol = voluntarioRepository.findVoluntarioByUsuarioSessao();

		StringBuilder msg = new StringBuilder();
		msg.append("--------------------------- HelpTheNext -------------------------");
		msg.append("\n");
		msg.append("\n");
		msg.append("\n");
		msg.append("Ol� " + selectedVaga.getOngEntity().getNomeONG());
		msg.append("\n");
		msg.append("\n");
		msg.append("O voluntario " + vol.getNome() + " tem interesse na sua vaga intitulada como "
				+ selectedVaga.getTitulo() + ".");
		msg.append("\n");
		msg.append("\n");
		msg.append("Segue abaixo o contado do voluntario:");
		msg.append("\n");
		msg.append("Telefone: " + vol.getTelefone());
		msg.append("\n");
		msg.append("Email: " + vol.getEmail());
		msg.append("\n");
		msg.append("\n");
		msg.append("--------------------------- HelpTheNext -------------------------");
		msg.append("\n");
		msg.append("\n");
		msg.append(
				"Por favor, n�o responda a este e-mail - que foi gerada a partir de uma conta que envia mensagens automaticamente e n�o pode receber respostas de volta.");

		String assunto = "[HelpTheNext] Um voluntario est� interessado na sua vaga!!";

		javaMailApp.enviarEmail(selectedVaga.getOngEntity().getEmail(), msg.toString(), assunto);
	}

	public void retornaVagasConformeBusca() {

		if (vagas == null || vagas.isEmpty()) {
			vagas = vagaRepository.findAll();
		}

		List<VagaModel> vagasConformeBusca = new ArrayList<>();
		for (VagaModel eventoModel : this.vagas) {
			if (TituloUteis.isSemelhantes(busca, eventoModel.getTitulo())) {
				vagasConformeBusca.add(eventoModel);
			}
		}
		
		vagas = vagasConformeBusca;
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

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public String getBusca() {
		return busca;
	}

}
