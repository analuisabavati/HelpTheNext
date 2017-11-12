package br.com.helpthenext.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

	private Boolean exibirBotaoEditar;

	private String busca;
	private String[] causas;
	private String[] habilidades;
	private String[] dias;
	private String[] periodos;
	private String trabalhoDistancia;

	@PostConstruct // executado na inicialização da classe
	public void init() {
		this.vagas = null;
		this.vagas = vagaRepository.findAll();
	}

	public void ativarBotoes() {
		ONGEntity ong = ongRepository.findONGByUsuarioSessao();
		if (ong != null && selectedVaga != null && selectedVaga.getOngEntity() != null
				&& selectedVaga.getOngEntity().getId() != null) {
			if (selectedVaga.getOngEntity() != null && selectedVaga.getOngEntity().getId().equals(ong.getId())) {
				exibirBotaoEditar = true;
			} else {
				exibirBotaoEditar = null;
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
		msg.append("Olá " + selectedVaga.getOngEntity().getNomeONG());
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
				"Por favor, não responda a este e-mail - que foi gerada a partir de uma conta que envia mensagens automaticamente e não pode receber respostas de volta.");

		String assunto = "[HelpTheNext] Um voluntario está interessado na sua vaga!!";

		javaMailApp.enviarEmail(selectedVaga.getOngEntity().getEmail(), msg.toString(), assunto);
	}

	public void retornaVagasConformeBusca() {
		vagas = vagaRepository.findAll();

		if (busca == null || busca.isEmpty()) {
			return;
		}

		List<VagaModel> vagasConformeBusca = new ArrayList<>();
		for (VagaModel vagaModel : this.vagas) {
			if (TituloUteis.isSemelhantes(busca, vagaModel.getTitulo())) {
				adicionaVaga(vagasConformeBusca, vagaModel);
			}
		}

		vagas = vagasConformeBusca;
	}

	public void filtrarVagas() {
		vagas = vagaRepository.findAll();

		List<VagaModel> vagasFiltradas = new ArrayList<>();

		int filtrosAtendidos = 0;
		for (VagaModel vaga : vagas) {
			if (contemCausa(vaga)) {
				filtrosAtendidos++;
			} else if (causas.length == 0) {
				filtrosAtendidos++;
			} 
			
			if (contemHabilidde(vaga)) {
				filtrosAtendidos++;
			} else if (habilidades.length == 0) {
				filtrosAtendidos++;
			}
			
			if (contemDias(vaga)) {
				filtrosAtendidos++;
			} else if (dias.length == 0) {
				filtrosAtendidos++;
			} 
			
			if (contemPeriodos(vaga)) {
				filtrosAtendidos++;
			} else if (periodos.length == 0) {
				filtrosAtendidos++;
			}
			
			if ((vaga.getTrabalhoDistancia() != null && vaga.getTrabalhoDistancia().equals(trabalhoDistancia)) || trabalhoDistancia.equals("T")) {
				filtrosAtendidos++;
			} else if (trabalhoDistancia.isEmpty()) {
				filtrosAtendidos++;
			} 
			
			if (filtrosAtendidos == 5) {
				adicionaVaga(vagasFiltradas, vaga);
			}
			
			filtrosAtendidos = 0;
		}

		vagas = vagasFiltradas;
	}

	private boolean contemPeriodos(VagaModel vaga) {
		boolean contem = false;
		for(int i = 0; i < periodos.length; i++) {
			if (vaga.getPeriodos() != null && vaga.getPeriodos() != null && Arrays.asList(vaga.getPeriodos()).contains(periodos[i])) {
				contem = true;
			}
		}
		return contem;
	}

	private boolean contemDias(VagaModel vaga) {
		boolean contem = false;
		for(int i = 0; i < dias.length; i++) {
			if (vaga.getDias() != null && Arrays.asList(vaga.getDias()).contains(dias[i])) {
				contem = true;
			}
		}
		return contem;
	}

	private boolean contemHabilidde(VagaModel vaga) {
		boolean contem = false;
		for(int i = 0; i < habilidades.length; i++) {
			if (vaga.getHabilidades() != null && Arrays.asList(vaga.getHabilidades()).contains(habilidades[i])) {
				contem = true;
			}
		}
		return contem;
	}

	private boolean contemCausa(VagaModel vaga) {
		boolean contem = false;
		for(int i = 0; i < causas.length; i++) {
			if (vaga.getCausas() != null && Arrays.asList(vaga.getCausas()).contains(causas[i])) {
				contem = true;
			}
		}
		return contem;
	}

	private void adicionaVaga(List<VagaModel> vagasFiltradas, VagaModel vaga) {
		if (!vagasFiltradas.contains(vaga)) {
			vagasFiltradas.add(vaga);
		}
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

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public String getBusca() {
		return busca;
	}

	public VagaRepository getVagaRepository() {
		return vagaRepository;
	}

	public void setVagaRepository(VagaRepository vagaRepository) {
		this.vagaRepository = vagaRepository;
	}

	public ONGRepository getOngRepository() {
		return ongRepository;
	}

	public void setOngRepository(ONGRepository ongRepository) {
		this.ongRepository = ongRepository;
	}

	public JavaMailApp getJavaMailApp() {
		return javaMailApp;
	}

	public void setJavaMailApp(JavaMailApp javaMailApp) {
		this.javaMailApp = javaMailApp;
	}

	public VoluntarioRepository getVoluntarioRepository() {
		return voluntarioRepository;
	}

	public void setVoluntarioRepository(VoluntarioRepository voluntarioRepository) {
		this.voluntarioRepository = voluntarioRepository;
	}

	public String[] getCausas() {
		return causas;
	}

	public void setCausas(String[] causas) {
		this.causas = causas;
	}

	public String[] getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(String[] habilidades) {
		this.habilidades = habilidades;
	}

	public String[] getDias() {
		return dias;
	}

	public void setDias(String[] dias) {
		this.dias = dias;
	}

	public String[] getPeriodos() {
		return periodos;
	}

	public void setPeriodos(String[] periodos) {
		this.periodos = periodos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTrabalhoDistancia() {
		return trabalhoDistancia;
	}

	public void setTrabalhoDistancia(String trabalhoDistancia) {
		this.trabalhoDistancia = trabalhoDistancia;
	}

	public Boolean getExibirBotaoEditar() {
		return exibirBotaoEditar;
	}

	public void setExibirBotaoEditar(Boolean exibirBotaoEditar) {
		this.exibirBotaoEditar = exibirBotaoEditar;
	}


}
