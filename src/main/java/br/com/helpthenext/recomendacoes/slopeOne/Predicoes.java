package br.com.helpthenext.recomendacoes.slopeOne;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Predicoes {

	HashMap<Integer, Double> preferenciasVoluntario = new HashMap<Integer, Double>();
	HashMap<Integer, Double> predicoes = new HashMap<Integer, Double>();

	Double mediaDiferencas[][];
	Double qntVolAvaliaramItem[][];

	int quantidadeItensAvaliados;

	String pathArquivoAvaliacoes;
	String pathArquivoDiferencas;

	public static void main(String args[]) {
		System.out.println("\n[SlopOne] -  Iniciando Predicoes  -");
		String pathArqDiferencas1 = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\slope-intermidiary-output.txt";
		String pathArqAvaliacoes1 = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\ratings.dat";

		long inicio = System.currentTimeMillis();
		Predicoes p = new Predicoes();
		p.calculaPredicoes(2, pathArqAvaliacoes1, pathArqDiferencas1);
		long fim = System.currentTimeMillis();

		System.out.println("\n[SlopOne] Tempo de execução: " + (fim - inicio) + " ms.");
	}

	public List<Long> calculaPredicoes(int idVoluntario, String pathArquivoAvaliacoes, String pathArquivoDiferencas) {

		this.pathArquivoAvaliacoes = pathArquivoAvaliacoes;
		this.pathArquivoDiferencas = pathArquivoDiferencas;

		getPreferenciasVoluntario(idVoluntario);
		lePrecalculaDiferencasEntreItens();
		zeraMatrizPredicoes();

		double qntAvaliacoesItem[] = new double[quantidadeItensAvaliados + 1];

		for (int j : preferenciasVoluntario.keySet()) {
			for (int k = 1; k <= quantidadeItensAvaliados; k++) {
				if (j != k) {
					if (itemNaoAvaliado(k)) {

						double valorPredicao = 0;

						if (k >= j) {
							valorPredicao = qntVolAvaliaramItem[j][k]
									* (-1 * mediaDiferencas[j][k] + preferenciasVoluntario.get(j).doubleValue());
						} else {
							valorPredicao = qntVolAvaliaramItem[j][k]
									* (mediaDiferencas[j][k] + preferenciasVoluntario.get(j).doubleValue());
						}

						qntAvaliacoesItem[k] = qntAvaliacoesItem[k] + qntVolAvaliaramItem[j][k];

						predicoes.put(k, predicoes.get(k).doubleValue() + valorPredicao);
					}
				}
			}
		}

		return calculaMedias(qntAvaliacoesItem);
	}

	private List<Long> calculaMedias(double[] qntAvaliacoesItem) {
		for (int aux : predicoes.keySet()) {
			predicoes.put(aux, predicoes.get(aux).doubleValue() / (qntAvaliacoesItem[aux]));
		}

		
		// Fazer sort de predicoes pela avaliacao
		
		List<Long> idItensRecomendados = new ArrayList<>();

		for (int aux : predicoes.keySet()) {
			double avaliacao = predicoes.get(aux).doubleValue();
			if (!Double.isNaN(avaliacao) && avaliacao >= new Double(3)) {
				idItensRecomendados.add(new Long(aux));
			}

			System.out.println(aux + " " + avaliacao);
		}

		return idItensRecomendados;
	}

	private boolean itemNaoAvaliado(int i) {
		return !preferenciasVoluntario.containsKey(i);
	}

	private void zeraMatrizPredicoes() {
		for (int aux = 1; aux <= quantidadeItensAvaliados; aux++) {
			predicoes.put(aux, new Double(0.0));
		}
	}

	public void getPreferenciasVoluntario(int voluntarioPredicoes) {
		try {

			FileInputStream fileInputStram = new FileInputStream(new File(pathArquivoAvaliacoes));
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStram);
			DataInputStream dataInputStram = new DataInputStream(bufferedInputStream);

			StringTokenizer linhaFormatada;
			int voluntarioTemp;

			while (dataInputStram.available() != 0) {

				linhaFormatada = retiraVirgula(getProximaLinha(dataInputStram));
				voluntarioTemp = getProximoInt(linhaFormatada);

				while (voluntarioTemp == voluntarioPredicoes) {

					preferenciasVoluntario.put(getProximoInt(linhaFormatada), getProximoDouble(linhaFormatada));

					linhaFormatada = retiraVirgula(getProximaLinha(dataInputStram));
					voluntarioTemp = getProximoInt(linhaFormatada);
				}
			}

			fileInputStram.close();
			bufferedInputStream.close();
			dataInputStram.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lePrecalculaDiferencasEntreItens() {
		try {

			FileInputStream fileInputStream = new FileInputStream(new File(pathArquivoDiferencas));
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

			String linha = getProximaLinha(dataInputStream);
			StringTokenizer token = new StringTokenizer(linha, "\t");

			quantidadeItensAvaliados = getProximoInt(token);

			mediaDiferencas = new Double[quantidadeItensAvaliados + 1][quantidadeItensAvaliados + 1];
			qntVolAvaliaramItem = new Double[quantidadeItensAvaliados + 1][quantidadeItensAvaliados + 1];

			for (int item1 = 1; item1 <= quantidadeItensAvaliados; item1++) {
				for (int item2 = 1; item2 <= quantidadeItensAvaliados; item2++) {
					mediaDiferencas[item1][item2] = new Double("0");
					qntVolAvaliaramItem[item1][item2] = new Double("0");
				}
			}

			while (dataInputStream.available() != 0) {

				linha = getProximaLinha(dataInputStream);
				token = new StringTokenizer(linha, "\t");

				int item1 = getProximoInt(token);
				int item2 = getProximoInt(token);

				mediaDiferencas[item1][item2] = getProximoDouble(token);

				linha = getProximaLinha(dataInputStream);
				token = new StringTokenizer(linha, "\t");

				item1 = getProximoInt(token);
				item2 = getProximoInt(token);

				qntVolAvaliaramItem[item1][item2] = getProximoDouble(token);
			}

			fileInputStream.close();
			bufferedInputStream.close();
			dataInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Integer getProximoInt(StringTokenizer token) {
		return new Integer(token.nextToken());
	}

	@SuppressWarnings("deprecation")
	private String getProximaLinha(DataInputStream dataInputStram) throws IOException {
		return dataInputStram.readLine();
	}

	private Double getProximoDouble(StringTokenizer stringTokenizer) {
		return new Double(stringTokenizer.nextToken());
	}

	private StringTokenizer retiraVirgula(String linha) {
		return new StringTokenizer(linha, ",");
	}

}
