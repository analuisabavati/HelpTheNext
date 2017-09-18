package br.com.helpthenext.uteis;

public class TituloUteis {

	public static boolean isSemelhantes(String titulo, String busca) {

		if (busca.equalsIgnoreCase(titulo)) {
			return true;
		}

		int auxiliar = 0;

		for (int i = 0; i < titulo.length(); i++) {
			if (existeLetra(titulo.substring(i), busca)) {
				auxiliar++;
			}
		}

		if (titulo.length() - auxiliar < 2) {
			return true;
		}

		
		return false;
	}

	public static boolean existeLetra(String letra, String palavra) {
		return palavra.contains(letra);
	}

}
