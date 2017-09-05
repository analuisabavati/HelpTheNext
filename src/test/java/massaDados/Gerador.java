package massaDados;

import java.util.Random;

public class Gerador {
	
	public static String gerarTelefone() {		
		Random numRandom = new Random();

		int n1 = numRandom.nextInt(10);
		int n2 = numRandom.nextInt(10);
		int n3 = numRandom.nextInt(10);
		int n4 = numRandom.nextInt(10);
		int n5 = numRandom.nextInt(10);
		int n6 = numRandom.nextInt(10);
		int n7 = numRandom.nextInt(10);
		int n8 = numRandom.nextInt(10);
		int n9 = numRandom.nextInt(10);
		int n10 = numRandom.nextInt(10);
		int n11 = numRandom.nextInt(10);

		return String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + String.valueOf(n4) + String.valueOf(n5)
				+ String.valueOf(n6) + String.valueOf(n7) + String.valueOf(n8) + String.valueOf(n9)
				+ String.valueOf(n10) + String.valueOf(n11);
	}


	public static String gerarCPF() {
		Random numRandom = new Random();

		int n1 = numRandom.nextInt(10);
		int n2 = numRandom.nextInt(10);
		int n3 = numRandom.nextInt(10);
		int n4 = numRandom.nextInt(10);
		int n5 = numRandom.nextInt(10);
		int n6 = numRandom.nextInt(10);
		int n7 = numRandom.nextInt(10);
		int n8 = numRandom.nextInt(10);
		int n9 = numRandom.nextInt(10);
		int n10 = numRandom.nextInt(10);
		int n11 = numRandom.nextInt(10);

		return String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + String.valueOf(n4) + String.valueOf(n5)
				+ String.valueOf(n6) + String.valueOf(n7) + String.valueOf(n8) + String.valueOf(n9)
				+ String.valueOf(n10) + String.valueOf(n11);
	}

	public static String gerarCEP() {
		Random numRandom = new Random();

		int n1 = numRandom.nextInt(10);
		int n2 = numRandom.nextInt(10);
		int n3 = numRandom.nextInt(10);
		int n4 = numRandom.nextInt(10);
		int n5 = numRandom.nextInt(10);
		int n6 = numRandom.nextInt(10);
		int n7 = numRandom.nextInt(10);
		int n8 = numRandom.nextInt(10);

		return String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + String.valueOf(n4) + String.valueOf(n5)
				+ String.valueOf(n6) + String.valueOf(n7) + String.valueOf(n8);

	}

	public static String gerarSenha() {
		int tamanhoSenha = 6;

		char[] chart = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
				'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z' };

		char[] senha = new char[tamanhoSenha];
		Random random = new Random();

		for (int x = 0; x < tamanhoSenha; x++) {
			senha[x] = chart[random.nextInt(chart.length)];
		}

		return new String(senha);
	}

	public static int numAleatorio(int minimo, int maximo) {
	    Random random = new Random();
	    return random.nextInt((maximo - minimo) + 1) + minimo;
	}
}

