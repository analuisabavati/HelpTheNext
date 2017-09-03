package br.com.helpthenext.recomendacoes.slopeOne;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Predicoes {

	Map<Integer, Float> preferenciasVoluntario = new HashMap<Integer, Float>();
	HashMap<Integer, Float> predicoes = new HashMap<Integer, Float>();

	float mediaDiferencas[][];
	float quantidadeVoluntarios[][];

	int quantidadeItens;

	String pathArquivoAvaliacoes;
	String pathArquivoDiferencas;

	public static void main(String args[]) {
		System.out.println("\n[SlopOne] -  Iniciando Predicoes  -");
		String pathArqDiferencas1 = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\slope-intermidiary-output.txt";
		String pathArqAvaliacoes1 = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\ratings.dat";

		long inicio = System.currentTimeMillis();
		new Predicoes(2, pathArqAvaliacoes1, pathArqDiferencas1);
		long fim = System.currentTimeMillis();

		System.out.println("\n[SlopOne] Tempo de execução: " + (fim - inicio) + " ms.");
	}

	public Predicoes(int idVoluntario, String pathArquivoAvaliacoes, String pathArquivoDiferencas) {

		this.pathArquivoAvaliacoes = pathArquivoAvaliacoes;
		this.pathArquivoDiferencas = pathArquivoDiferencas;

		getPreferenciasVoluntario(idVoluntario);
		lePrecalculaDiferencasEntreItens();

		float totalFreq[] = new float[quantidadeItens + 1];

		for (int j = 1; j <= quantidadeItens; j++) {
			predicoes.put(j, 0.0f);
		}

		for (int j : preferenciasVoluntario.keySet()) {
			for (int k = 1; k <= quantidadeItens; k++) {
				if (j != k) {
					if (!preferenciasVoluntario.containsKey(k)) {
						
						float valorPredicao = 0;
					
						if (k < j) {
							valorPredicao = quantidadeVoluntarios[j][k] * (mediaDiferencas[j][k] + preferenciasVoluntario.get(j).floatValue());
						} else {
							valorPredicao = quantidadeVoluntarios[j][k] * (-1 * mediaDiferencas[j][k] + preferenciasVoluntario.get(j).floatValue());
						}
						
						totalFreq[k] = totalFreq[k] + quantidadeVoluntarios[j][k];
						
						predicoes.put(k, predicoes.get(k).floatValue() + valorPredicao);
					}
				}
			}
		}

		/* Calculate the average */
		for (int j : predicoes.keySet()) {
			predicoes.put(j, predicoes.get(j).floatValue() / (totalFreq[j]));
		}

		/* Fill the predictions vector with the already known rating values */
		for (int j : preferenciasVoluntario.keySet()) {
			predicoes.put(j, preferenciasVoluntario.get(j));
		}

		/* Print predictions */
		System.out.println("\n" + "#### Predictions #### ");
		for (int j : predicoes.keySet()) {
			System.out.println(j + " " + predicoes.get(j).floatValue());
		}
	}

	@SuppressWarnings("deprecation")
	public void getPreferenciasVoluntario(int voluntarioPredicoes) {
		try {

			File file = new File(pathArquivoAvaliacoes);
			FileInputStream fileInputStram = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStram);
			DataInputStream dataInputStram = new DataInputStream(bufferedInputStream);

			while (dataInputStram.available() != 0) {

				String linha = dataInputStram.readLine();
				StringTokenizer token = new StringTokenizer(linha, ",");
				int voluntarioTemp = Integer.parseInt(token.nextToken());

				while (voluntarioTemp == voluntarioPredicoes) {

					preferenciasVoluntario.put(Integer.parseInt(token.nextToken()),
							Float.parseFloat(token.nextToken()));

					linha = dataInputStram.readLine();
					token = new StringTokenizer(linha, ",");
					voluntarioTemp = Integer.parseInt(token.nextToken());
				}
			}

			fileInputStram.close();
			bufferedInputStream.close();
			dataInputStram.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings("deprecation")
	public void lePrecalculaDiferencasEntreItens() {
		try {

			File file = new File(pathArquivoDiferencas);
			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

			String linha = dataInputStream.readLine();
			StringTokenizer token = new StringTokenizer(linha, "\t");

			quantidadeItens = Integer.parseInt(token.nextToken());

			mediaDiferencas = new float[quantidadeItens + 1][quantidadeItens + 1];
			quantidadeVoluntarios = new float[quantidadeItens + 1][quantidadeItens + 1];

			for (int item1 = 1; item1 <= quantidadeItens; item1++) {
				for (int item2 = 1; item2 <= quantidadeItens; item2++) {
					mediaDiferencas[item1][item2] = 0;
					quantidadeVoluntarios[item1][item2] = 0;
				}
			}

			while (dataInputStream.available() != 0) {

				linha = dataInputStream.readLine();
				token = new StringTokenizer(linha, "\t");
				
				int item1 = Integer.parseInt(token.nextToken());
				int item2 = Integer.parseInt(token.nextToken());

				mediaDiferencas[item1][item2] = Float.parseFloat(token.nextToken());

				linha = dataInputStream.readLine();
				token = new StringTokenizer(linha, "\t");
				
				item1 = Integer.parseInt(token.nextToken());
				item2 = Integer.parseInt(token.nextToken());

				quantidadeVoluntarios[item1][item2] = Float.parseFloat(token.nextToken());
			}

			fileInputStream.close();
			bufferedInputStream.close();
			dataInputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
