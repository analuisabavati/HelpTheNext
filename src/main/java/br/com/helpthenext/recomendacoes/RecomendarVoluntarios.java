package br.com.helpthenext.recomendacoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.mail.JavaMailApp;
import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.uteis.ComparadorVoluntarios;

@Named(value = "recomendarVoluntarios")
@SessionScoped
public class RecomendarVoluntarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient VoluntarioRepository voluntarioRepository;

	@Inject
	transient ONGRepository ongRepository;

	@Inject
	transient JavaMailApp javaMailApp;

	@Produces
	List<VoluntarioModel> voluntariosRecomendados;

	VoluntarioModel selectedVoluntario;

	VagaModel vaga;

	public void recomendarVoluntarios(VagaModel vaga) {

		this.vaga = vaga;

		List<VoluntarioModel> voluntariosEncontrados = new ArrayList<>();

		if (vaga.getTrabalhoDistancia().equals("S")) {
			voluntariosEncontrados = voluntarioRepository.findVoluntariosByTrabalhoDistancia();
		} else {
			ONGEntity ong = ongRepository.findONGByUsuarioSessao();
			voluntariosEncontrados = voluntarioRepository.findVoluntariosByCidadeEstado(ong.getCidade(),
					ong.getEstado());
		}

		if (voluntariosEncontrados == null || voluntariosEncontrados.isEmpty()) {
			this.voluntariosRecomendados = new ArrayList<>();
			return;
		}

		int somatorio = 0;

		List<VoluntarioModel> voluntariosRecomendados = new ArrayList<>();
		for (VoluntarioModel voluntarioModel : voluntariosEncontrados) {

			if (voluntarioModel.getDisponibilidadeDias() != null && voluntarioModel.getDisponibilidadeDias() != null) {
				for (String dia : voluntarioModel.getDisponibilidadeDias()) {
					for (String diaVol : voluntarioModel.getDisponibilidadeDias()) {
						if (dia.equals(diaVol)) {
							somatorio = somatorio + 3;
						}
					}
				}
			}

			if (voluntarioModel.getDisponibilidadePeriodos() != null
					&& voluntarioModel.getDisponibilidadePeriodos() != null) {
				for (String periodo : voluntarioModel.getDisponibilidadePeriodos()) {
					for (String periodoVol : voluntarioModel.getDisponibilidadePeriodos()) {
						if (periodo.equals(periodoVol)) {
							somatorio = somatorio + 3;
						}
					}
				}
			}

			if (voluntarioModel.getHabilidades() != null && voluntarioModel.getHabilidades() != null) {
				for (String periodo : voluntarioModel.getHabilidades()) {
					for (String periodoVol : voluntarioModel.getHabilidades()) {
						if (periodo.equals(periodoVol)) {
							somatorio = somatorio + 2;
						}
					}
				}
			}

			if (voluntarioModel.getCausas() != null && voluntarioModel.getCausas() != null) {
				for (String periodo : voluntarioModel.getCausas()) {
					for (String periodoVol : voluntarioModel.getCausas()) {
						if (periodo.equals(periodoVol)) {
							somatorio = somatorio + 1;
						}
					}
				}
			}

			if (somatorio != 0) {
				voluntarioModel.setPontos(somatorio);
				voluntariosRecomendados.add(voluntarioModel);
			}

			somatorio = 0;
		}

		Collections.sort(voluntariosRecomendados, new ComparadorVoluntarios());
		
		this.voluntariosRecomendados = voluntariosRecomendados;
	}

	public void enviarEmail() {

		ONGEntity ong = ongRepository.findONGByUsuarioSessao();

		StringBuilder msg = new StringBuilder();
		msg.append("--------------------------- HelpTheNext -------------------------");
		msg.append("\n");
		msg.append("\n");
		msg.append("\n");
		msg.append("Olá " + selectedVoluntario.getNome());
		msg.append("\n");
		msg.append("\n");
		msg.append("A ONG " + ong.getNomeONG() + " cadastrou uma nova vaga de voluntariado intitulada "
				+ vaga.getTitulo() + ", compativel com o seu perfil no site.");
		msg.append("\n");
		msg.append("\n");
		msg.append("Segue abaixo o contato da ONG:");
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

		String assunto = "[HelpTheNext] Há uma nova vaga compativel com seu perfil!!";

		javaMailApp.enviarEmail(selectedVoluntario.getEmail(), msg.toString(), assunto);
	}

	public VoluntarioRepository getVoluntarioRepository() {
		return voluntarioRepository;
	}

	public void setVoluntarioRepository(VoluntarioRepository voluntarioRepository) {
		this.voluntarioRepository = voluntarioRepository;
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

	public List<VoluntarioModel> getVoluntariosRecomendados() {
		return voluntariosRecomendados;
	}

	public void setVoluntariosRecomendados(List<VoluntarioModel> voluntariosRecomendados) {
		this.voluntariosRecomendados = voluntariosRecomendados;
	}

	public VoluntarioModel getSelectedVoluntario() {
		return selectedVoluntario;
	}

	public void setSelectedVoluntario(VoluntarioModel selectedVoluntario) {
		this.selectedVoluntario = selectedVoluntario;
	}

	public VagaModel getVaga() {
		return vaga;
	}

	public void setVaga(VagaModel vaga) {
		this.vaga = vaga;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
