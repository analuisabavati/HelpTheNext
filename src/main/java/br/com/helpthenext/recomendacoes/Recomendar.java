package br.com.helpthenext.recomendacoes;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.VoluntarioRepository;

@SessionScoped
@Named(value = "recomendar")
public class Recomendar {

	@Inject
	transient VoluntarioRepository voluntarioRepository;
	
	private VagaModel vaga;

	public List<VoluntarioModel> recomendarVoluntarios() {

		List<VoluntarioModel> voluntariosEncontrados = new ArrayList<>();

		if (vaga.getTrabalhoDistancia().equals("Sim")) {
			voluntariosEncontrados = voluntarioRepository.getVoluntariosTrabalhoDistancia();
		} else {
			voluntariosEncontrados = voluntarioRepository.getVoluntariosCidadeEstado(vaga.getOngEntity().getCidade(), vaga.getOngEntity()
					.getEstado());
		}

		int somatorio = 0;

		List<VoluntarioModel> voluntariosRecomendados = new ArrayList<>();
		for (VoluntarioModel voluntarioModel : voluntariosEncontrados) {
			for (String dia : voluntarioModel.getDisponibilidadeDias()) {
				for (String diaVol : voluntarioModel.getDisponibilidadeDias()) {
					if (dia.equals(diaVol)) {
						somatorio = somatorio + 3;
					}
				}
			}
			
			for (String periodo : voluntarioModel.getDisponibilidadePeriodos()) {
				for (String periodoVol : voluntarioModel.getDisponibilidadePeriodos()) {
					if (periodo.equals(periodoVol)) {
						somatorio = somatorio + 3;
					}
				}
			}
			
			for (String periodo : voluntarioModel.getHabilidades()) {
				for (String periodoVol : voluntarioModel.getHabilidades()) {
					if (periodo.equals(periodoVol)) {
						somatorio = somatorio + 2;
					}
				}
			}
			
			for (String periodo : voluntarioModel.getCausas()) {
				for (String periodoVol : voluntarioModel.getCausas()) {
					if (periodo.equals(periodoVol)) {
						somatorio = somatorio + 1;
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
		
		return voluntariosRecomendados;
	}
}
