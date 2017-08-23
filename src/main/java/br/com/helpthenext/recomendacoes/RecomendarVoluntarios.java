package br.com.helpthenext.recomendacoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.ONGEntity;

@SessionScoped
@Named(value = "recomendarVoluntarios")
public class RecomendarVoluntarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient VoluntarioRepository voluntarioRepository;

	@Inject
	transient ONGRepository ongRepository;

	List<VoluntarioModel> voluntariosRecomendados;

	public void recomendarVoluntarios(VagaModel vaga) {

		List<VoluntarioModel> voluntariosEncontrados = new ArrayList<>();

		if (vaga.getTrabalhoDistancia().equals("S")) {
			voluntariosEncontrados = voluntarioRepository.getVoluntariosTrabalhoDistancia();
		} else {
			ONGEntity ong = ongRepository.getONGByUsuarioSessao();
			voluntariosEncontrados = voluntarioRepository.getVoluntariosCidadeEstado(ong.getCidade(), ong.getEstado());
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

		// ordenar

		this.voluntariosRecomendados = voluntariosRecomendados;
	}

	public VoluntarioRepository getVoluntarioRepository() {
		return voluntarioRepository;
	}

	public void setVoluntarioRepository(VoluntarioRepository voluntarioRepository) {
		this.voluntarioRepository = voluntarioRepository;
	}

	public List<VoluntarioModel> getVoluntariosRecomendados() {
		return voluntariosRecomendados;
	}

	public void setVoluntariosRecomendados(List<VoluntarioModel> voluntariosRecomendados) {
		this.voluntariosRecomendados = voluntariosRecomendados;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
