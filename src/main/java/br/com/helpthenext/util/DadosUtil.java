package br.com.helpthenext.util;

public class DadosUtil {

	public static String formataHabilidades(String habilidades) {
		if (habilidades == null) {
			return null;
		}
		
		habilidades = habilidades.replace("10", "Sa�de e Psicologia");
		habilidades = habilidades.replace("11", "Outros");	
		habilidades = habilidades.replace("0", "Artes e Artesanato");
		habilidades = habilidades.replace("1", "Comunica��o");
		habilidades = habilidades.replace("2", "Dan�a e Musica");
		habilidades = habilidades.replace("3", "Direito");
		habilidades = habilidades.replace("4", "Educa��o");
		habilidades = habilidades.replace("5", "Esportes");
		habilidades = habilidades.replace("6", "Gastronomia");
		habilidades = habilidades.replace("7", "Gest�o");
		habilidades = habilidades.replace("8", "Idiomas");
		habilidades = habilidades.replace("9", "Inform�tica e Eletr�nica");

		habilidades = habilidades.replace(",", " - ");
		habilidades = habilidades.replace("[", " ");
		habilidades = habilidades.replace("]", " ");

		return habilidades;
	}

	public static String formataCausas(String x) {
		if (x == null) {
			return null;
		}

		x = x.replace("10", "Consumo Consciente");
		x = x.replace("11", "Capacita��o Profissional");
		x = x.replace("12", "Combate � probreza");
		x = x.replace("13", "Outras");
		x = x.replace("0", "Pessoas com defici�ncia");
		x = x.replace("1", "Sa�de");
		x = x.replace("2", "Prote��o Animal");
		x = x.replace("3", "Participa��o Cidad�");
		x = x.replace("4", "Meio Ambiente");
		x = x.replace("5", "Idosos");
		x = x.replace("6", "Educa��o");
		x = x.replace("7", "Defesa de direitos");
		x = x.replace("8", "Cultura, Esporte e Arte");
		x = x.replace("9", "Crian�as e Jovens");
		
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
		x = x.replace("2", "Ter�a");
		x = x.replace("3", "Quarta");
		x = x.replace("4", "Quinta");
		x = x.replace("5", "Sexta");
		x = x.replace("6", "S�bado");
			
		x = x.replace(",", " - ");
		x = x.replace("[", " ");
		x = x.replace("]", " ");
		
		return x;
	}

	public static String formataPeriodos(String x) {
		if (x == null) {
			return null;
		}
		
		x = x.replace("0", "Manh�");
		x = x.replace("1", "Tarde");
		x = x.replace("2", "Noite");
			
		x = x.replace(",", " - ");
		x = x.replace("[", " ");
		x = x.replace("]", " ");
		
		return x;
	}
}
