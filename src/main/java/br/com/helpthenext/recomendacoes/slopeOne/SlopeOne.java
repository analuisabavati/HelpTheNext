package br.com.helpthenext.recomendacoes.slopeOne;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SlopeOne {

	HashMap<Integer, Map<Integer, Double>> matrizVoluntarioItemAvaliacao;
	double diffMediaAvaliacoes[][];
	long quantidadeAvaliacoes[][];

	int quantidadeItens = 0;

	String pathArquivoAvaliacoes;
	String pathArquivoDiferencas;

	public void calculaMatrizDiferencas(String pathArquivoAvaliacoes, String pathArquivoDiferencas) {

		this.pathArquivoAvaliacoes = pathArquivoAvaliacoes;
		this.pathArquivoDiferencas = pathArquivoDiferencas;

		leArquivoCriaMatrizVoluntarioItemAvaliacao();
		calculaMatrizDiferencas();
		escreveMatrizDiferencas();
	}

	public void leArquivoCriaMatrizVoluntarioItemAvaliacao() {
		try {

			FileInputStream fileInputStream = new FileInputStream(new File(pathArquivoAvaliacoes));
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

			matrizVoluntarioItemAvaliacao = new HashMap<Integer, Map<Integer, Double>>();

			StringTokenizer linha;
			int voluntario;
			int voluntarioTemp;
			int item;

			while (dataInputStream.available() != 0) {

				linha = removeVirgulas(getProximaLinha(dataInputStream));
				voluntario = getProximoTokenInt(linha);

				if (voluntario == -1) {
					break;
				}

				voluntarioTemp = voluntario;

				matrizVoluntarioItemAvaliacao.put(voluntario, new HashMap<Integer, Double>());

				while (voluntario == voluntarioTemp) {

					item = getProximoTokenInt(linha);

					matrizVoluntarioItemAvaliacao.get(voluntario).put(item, getProximoTokenDouble(linha));

					if (dataInputStream.available() != 0) {
						linha = removeVirgulas(getProximaLinha(dataInputStream));
						voluntarioTemp = getProximoTokenInt(linha);

						if (voluntario == -1) {
							break;
						}

					} else {
						voluntarioTemp = -1;
					}

					quantidadeItens = quantidadeItens < item ? item : quantidadeItens;
				}
			}

			fileInputStream.close();
			bufferedInputStream.close();
			dataInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private String getProximaLinha(DataInputStream dataInputStream) throws IOException {
		return dataInputStream.readLine();
	}

	public void calculaMatrizDiferencas() {

		diffMediaAvaliacoes = new double[quantidadeItens + 1][quantidadeItens + 1];
		quantidadeAvaliacoes = new long[quantidadeItens + 1][quantidadeItens + 1];

		calculaDiferencas();
		calculaMedias();
	}

	private void calculaDiferencas() {
		/*
		 * matrizVoluntarioItemAvaliacao = soma das diffs das notas dadas por
		 * tds vol pra os itens 1 e 2 quantidadeAvaliacoes = qnt de vol que
		 * avaliaram os itens
		 */

		double diffAvaliacoesItem1Item2;

		for (int voluntario : matrizVoluntarioItemAvaliacao.keySet()) {
			for (int item1 : matrizVoluntarioItemAvaliacao.get(voluntario).keySet()) {
				for (int item2 : matrizVoluntarioItemAvaliacao.get(voluntario).keySet()) {

					diffAvaliacoesItem1Item2 = matrizVoluntarioItemAvaliacao.get(voluntario).get(item1).doubleValue()
							- (matrizVoluntarioItemAvaliacao.get(voluntario).get(item2).doubleValue());

					diffMediaAvaliacoes[item1][item2] = diffMediaAvaliacoes[item1][item2] + diffAvaliacoesItem1Item2;

					quantidadeAvaliacoes[item1][item2] = quantidadeAvaliacoes[item1][item2] + 1;
				}
			}
		}
	}

	private void calculaMedias() {
		/*
		 * Calcula diferenca media das avaliacoes ( diffMediaAvaliacoes /
		 * quantidadeAvaliacoes)
		 */

		for (int item1 = 1; item1 <= quantidadeItens; item1++) {
			for (int item2 = item1; item2 <= quantidadeItens; item2++) {
				if (quantidadeAvaliacoes[item1][item2] > 0) {
					diffMediaAvaliacoes[item1][item2] = diffMediaAvaliacoes[item1][item2]
							/ quantidadeAvaliacoes[item1][item2];
				}
			}
		}
	}

	private void escreveMatrizDiferencas() {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(pathArquivoDiferencas);

			fileOutputStream.write(String.valueOf(quantidadeItens).getBytes());
			fileOutputStream.write(String.valueOf("\n").getBytes());

			for (int item1 = 1; item1 <= quantidadeItens; item1++) {
				for (int item2 = item1; item2 <= quantidadeItens; item2++) {

					if (!Double.isNaN(diffMediaAvaliacoes[item1][item2])) {
						fileOutputStream.write(String.valueOf(item1).getBytes());
						fileOutputStream.write(String.valueOf("\t").getBytes());
						fileOutputStream.write(String.valueOf(item2).getBytes());
						fileOutputStream.write(String.valueOf("\t").getBytes());
						fileOutputStream.write(String.valueOf(diffMediaAvaliacoes[item1][item2]).getBytes());
						fileOutputStream.write(String.valueOf("\n").getBytes());

						fileOutputStream.write(String.valueOf(item1).getBytes());
						fileOutputStream.write(String.valueOf("\t").getBytes());
						fileOutputStream.write(String.valueOf(item2).getBytes());
						fileOutputStream.write(String.valueOf("\t").getBytes());
						fileOutputStream.write(String.valueOf(quantidadeAvaliacoes[item1][item2]).getBytes());
						fileOutputStream.write(String.valueOf("\n").getBytes());
					}
				}
			}

			fileOutputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private StringTokenizer removeVirgulas(String linha) {
		return new StringTokenizer(linha, ",");
	}

	private Double getProximoTokenDouble(StringTokenizer t) {
		return new Double(t.nextToken());
	}

	private Integer getProximoTokenInt(StringTokenizer t) {
		return new Integer(t.nextToken());
	}
}