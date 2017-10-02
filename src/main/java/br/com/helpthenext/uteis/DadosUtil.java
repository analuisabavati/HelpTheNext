package br.com.helpthenext.uteis;

public class DadosUtil {

	public static String formataHabilidades(String habilidades) {
		if (habilidades == null) {
			return null;
		}
		
		habilidades = habilidades.replace("0", "Artes e Artesanato");
		habilidades = habilidades.replace("1", "Comunicação");
		habilidades = habilidades.replace("2", "Dança e Musica");
		habilidades = habilidades.replace("3", "Direito");
		habilidades = habilidades.replace("4", "Educação");
		habilidades = habilidades.replace("5", "Esportes");
		habilidades = habilidades.replace("6", "Gastronomia");
		habilidades = habilidades.replace("7", "Gestão");
		habilidades = habilidades.replace("8", "Idiomas");
		habilidades = habilidades.replace("9", "Informática e Eletrônica");
		habilidades = habilidades.replace("10", "Saúde e Psicologia");
		habilidades = habilidades.replace("11", "Idiomas");
		habilidades = habilidades.replace("12", "Outros");
		habilidades = habilidades.replace(",", " - ");
		habilidades = habilidades.replace("[", " ");
		habilidades = habilidades.replace("]", " ");

		return habilidades;
	}

	public static String formataCausas(String x) {
		if (x == null) {
			return null;
		}

		x = x.replace("0", "Pessoas com deficiência");
		x = x.replace("1", "Saúde");
		x = x.replace("2", "Proteção Animal");
		x = x.replace("3", "Participação Cidadã");
		x = x.replace("4", "Meio Ambiente");
		x = x.replace("5", "Idosos");
		x = x.replace("6", "Educação");
		x = x.replace("7", "Defesa de direitos");
		x = x.replace("8", "Cultura, Esporte e Arte");
		x = x.replace("9", "Crianças e Jovens");
		x = x.replace("10", "Consumo Consciente");
		x = x.replace("11", "Capacitação Profissional");
		x = x.replace("12", "Combate à probreza");
		x = x.replace("13", "Outras");

		x = x.replace(",", " - ");
		x = x.replace("[", " ");
		x = x.replace("]", " ");

		return x;
	}

	public static String formataDias(String x) {
		if (x == null) {
			return null;
		}
		
		x = x.replace("0", "Domingo");
		x = x.replace("1", "Segunda");
		x = x.replace("2", "Terça");
		x = x.replace("3", "Quarta");
		x = x.replace("4", "Quinta");
		x = x.replace("5", "Sexta");
		x = x.replace("6", "Sábado");
			
		x = x.replace(",", " - ");
		x = x.replace("[", " ");
		x = x.replace("]", " ");
		
		return x;
	}

	public static String formataPeriodos(String x) {
		if (x == null) {
			return null;
		}
		
		x = x.replace("0", "Manhã");
		x = x.replace("1", "Tarde");
		x = x.replace("2", "Noite");
			
		x = x.replace(",", " - ");
		x = x.replace("[", " ");
		x = x.replace("]", " ");
		
		return x;
	}
}
