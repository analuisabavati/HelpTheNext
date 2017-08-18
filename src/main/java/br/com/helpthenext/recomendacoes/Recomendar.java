package br.com.helpthenext.recomendacoes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.helpthenext.model.VagaModel;
import br.com.helpthenext.model.VoluntarioModel;
import br.com.helpthenext.repository.VoluntarioRepository;

public class Recomendar {
	
	@Inject
	transient VoluntarioRepository voluntarioRepository;

	public List<VoluntarioModel> recomendarVoluntarios (VagaModel vaga) {
		
		List<VoluntarioModel> voluntariosEncontrados = new ArrayList<>();
		
		if (vaga.getTrabalhoDistancia().equals("Sim")) {
			voluntariosEncontrados = voluntarioRepository.getVoluntariosTrabalhoDistancia();
		} else {
			voluntariosEncontrados = voluntarioRepository.getVoluntariosCidadeEstado(vaga.getOngEntity().getCidade(), vaga.getOngEntity().getEstado());
		}

		
		List<VoluntarioModel> voluntariosRecomendados = new ArrayList<>();
		for (VoluntarioModel voluntarioModel : voluntariosEncontrados) {
			
			
			
			

			
		}
		

		return voluntariosRecomendados;
	}
}
