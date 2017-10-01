package br.com.helpthenext.uteis;

public class DadosUtil {

	public static String formataHabilidades(String habilidades) {
		if (habilidades == null) {
			return null;
		}

		habilidades = habilidades.replace("COMUNICACAO", "Comunicação");
		habilidades = habilidades.replace("DANCA_MUSICA", "Dança e Musica");
		habilidades = habilidades.replace("DIREITO", "Direito");
		habilidades = habilidades.replace("EDUCACAO", "Educação");
		habilidades = habilidades.replace("ESPORTES", "Esportes");
		habilidades = habilidades.replace("GASTRONOMIA", "Gastronomia");
		habilidades = habilidades.replace("GESTAO", "Gestão");
		habilidades = habilidades.replace("IDIOMAS", "Idiomas");
		habilidades = habilidades.replace("INFORMATICA_ELETRONICA", "Informática e Eletrônica");
		habilidades = habilidades.replace("SAUDE_PISCIOLOGIA", "Saúde e Psicologia");
		habilidades = habilidades.replace("IDIOMAS", "Idiomas");
		habilidades = habilidades.replace("ARTES_ARTESANATO", "Artes e Artesanato");
		habilidades = habilidades.replace("OUTROS", "Outros");
		habilidades = habilidades.replace(",", " - ");
		habilidades = habilidades.replace("[", " ");
		habilidades = habilidades.replace("]", " ");

		return habilidades;
	}

	public static String formataCausas(String x) {
		if (x == null) {
			return null;
		}

		x = x.replace("PESSOAS_COM_DEFICIENCIA", "Pessoas com deficiência");
		x = x.replace("SAUDE", "Saúde");
		x = x.replace("PROTECAO_ANIMAL", "Proteção Animal");
		x = x.replace("PARTICIPACAO_CIDADA", "Participação Cidadã");
		x = x.replace("MEIO_AMBIENTE", "Meio Ambiente");
		x = x.replace("IDOSOS", "Idosos");
		x = x.replace("EDUCACAO", "Educação");
		x = x.replace("DEFESA_DE_DIREITOS", "Defesa de direitos");
		x = x.replace("CULTURA_ESPORTE_ARTE", "Cultura, Esporte e Arte");
		x = x.replace("CRIANCAS_JOVENS", "Crianças e Jovens");
		x = x.replace("CONSUMO_CONSCIENTE", "Consumo Consciente");
		x = x.replace("CAPACITAÇÃO_PROFISSIONAL", "Capacitação Profissional");
		x = x.replace("COMBATE__PROBREZA", "Combate à probreza");
		x = x.replace("OUTRAS", "Outras");

		x = x.replace(",", " - ");
		x = x.replace("[", " ");
		x = x.replace("]", " ");

		return x;
	}

	public static String formataDias(String x) {
		if (x == null) {
			return null;
		}
		
		x = x.replace("DOMINGO", "Domingo");
		x = x.replace("SEGUNDA_FEIRA", "Segunda");
		x = x.replace("TERCA_FEIRA", "Terça");
		x = x.replace("QUARTA_FEIRA", "Quarta");
		x = x.replace("QUINTA_FEIRA", "Quinta");
		x = x.replace("SEXTA_FEIRA", "Sexta");
		x = x.replace("SABADO", "Sábado");
			
		x = x.replace(",", " - ");
		x = x.replace("[", " ");
		x = x.replace("]", " ");
		
		return x;
	}

	public static String formataPeriodos(String x) {
		if (x == null) {
			return null;
		}
		
		x = x.replace("MANHA", "Manhã");
		x = x.replace("TARDE", "Tarde");
		x = x.replace("NOITE", "Noite");
			
		x = x.replace(",", " - ");
		x = x.replace("[", " ");
		x = x.replace("]", " ");
		
		return x;
	}
}
