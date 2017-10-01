package br.com.helpthenext.uteis;

public class DadosUtil {

	public static String formataHabilidades(String habilidades) {
		if (habilidades == null) {
			return null;
		}

		habilidades = habilidades.replace("COMUNICACAO", "Comunica��o");
		habilidades = habilidades.replace("DANCA_MUSICA", "Dan�a e Musica");
		habilidades = habilidades.replace("DIREITO", "Direito");
		habilidades = habilidades.replace("EDUCACAO", "Educa��o");
		habilidades = habilidades.replace("ESPORTES", "Esportes");
		habilidades = habilidades.replace("GASTRONOMIA", "Gastronomia");
		habilidades = habilidades.replace("GESTAO", "Gest�o");
		habilidades = habilidades.replace("IDIOMAS", "Idiomas");
		habilidades = habilidades.replace("INFORMATICA_ELETRONICA", "Inform�tica e Eletr�nica");
		habilidades = habilidades.replace("SAUDE_PISCIOLOGIA", "Sa�de e Psicologia");
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

		x = x.replace("PESSOAS_COM_DEFICIENCIA", "Pessoas com defici�ncia");
		x = x.replace("SAUDE", "Sa�de");
		x = x.replace("PROTECAO_ANIMAL", "Prote��o Animal");
		x = x.replace("PARTICIPACAO_CIDADA", "Participa��o Cidad�");
		x = x.replace("MEIO_AMBIENTE", "Meio Ambiente");
		x = x.replace("IDOSOS", "Idosos");
		x = x.replace("EDUCACAO", "Educa��o");
		x = x.replace("DEFESA_DE_DIREITOS", "Defesa de direitos");
		x = x.replace("CULTURA_ESPORTE_ARTE", "Cultura, Esporte e Arte");
		x = x.replace("CRIANCAS_JOVENS", "Crian�as e Jovens");
		x = x.replace("CONSUMO_CONSCIENTE", "Consumo Consciente");
		x = x.replace("CAPACITA��O_PROFISSIONAL", "Capacita��o Profissional");
		x = x.replace("COMBATE__PROBREZA", "Combate � probreza");
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
		x = x.replace("TERCA_FEIRA", "Ter�a");
		x = x.replace("QUARTA_FEIRA", "Quarta");
		x = x.replace("QUINTA_FEIRA", "Quinta");
		x = x.replace("SEXTA_FEIRA", "Sexta");
		x = x.replace("SABADO", "S�bado");
			
		x = x.replace(",", " - ");
		x = x.replace("[", " ");
		x = x.replace("]", " ");
		
		return x;
	}

	public static String formataPeriodos(String x) {
		if (x == null) {
			return null;
		}
		
		x = x.replace("MANHA", "Manh�");
		x = x.replace("TARDE", "Tarde");
		x = x.replace("NOITE", "Noite");
			
		x = x.replace(",", " - ");
		x = x.replace("[", " ");
		x = x.replace("]", " ");
		
		return x;
	}
}
