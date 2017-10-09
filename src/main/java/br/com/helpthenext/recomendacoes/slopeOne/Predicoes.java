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

	HashMap<Integer, Double> matrizAvaliacaoItemVoluntario = new HashMap<Integer, Double>();
	HashMap<Integer, Double> predicoes = new HashMap<Integer, Double>();

	Double diffMediaAvaliacoesItem1Item2[][];
	Double qntVolAvaliaramItem1Item2[][];

	int quantidadeItensAvaliados;

	String pathArquivoAvaliacoes;
	String pathArquivoDiferencas;

	public List<Long> calculaPredicoes(int idVoluntario, String pathArquivoAvaliacoes, String pathArquivoDiferencas) {

		this.pathArquivoAvaliacoes = pathArquivoAvaliacoes;
		this.pathArquivoDiferencas = pathArquivoDiferencas;

		getAvaliacoesFeitasPeloVoluntario(idVoluntario);
		leArquivoDiffMediaAvaliacoes();
		zeraMatrizPredicoes();

		double qntAvaliacoesItem[] = new double[quantidadeItensAvaliados + 1];

		for (int itemAvaliado : matrizAvaliacaoItemVoluntario.keySet()) {

			for (int itemX = 1; itemX <= quantidadeItensAvaliados; itemX++) {

				if (itemX != itemAvaliado && isItemNaoAvaliado(itemX)) {
					double valorPredicao = 0;

					/*
					 * Calcula a nota que o voluntario daria ao itemX baseado no
					 * itemAvaliado
					 * 
					 */

					if (itemX >= itemAvaliado) {
						valorPredicao = removeValorPredicao(itemAvaliado, itemX);
					} else {
						valorPredicao = calculaValorPredicao(itemAvaliado, itemX);
					}

					qntAvaliacoesItem[itemX] = qntAvaliacoesItem[itemX] + qntVolAvaliaramItem1Item2[itemAvaliado][itemX];

					predicoes.put(itemX, predicoes.get(itemX).doubleValue() + valorPredicao);
				}
			}
		}

		calculaMedias(qntAvaliacoesItem);

		return getIdsItensRecomendados();
	}

	private double calculaValorPredicao(int itemAvaliado, int itemX) {
		return (diffMediaAvaliacoesItem1Item2[itemAvaliado][itemX]
				+ matrizAvaliacaoItemVoluntario.get(itemAvaliado).doubleValue())
				* qntVolAvaliaramItem1Item2[itemAvaliado][itemX];
	}

	private double removeValorPredicao(int itemAvaliado, int itemX) {
		return (-1 * diffMediaAvaliacoesItem1Item2[itemAvaliado][itemX]
				+ matrizAvaliacaoItemVoluntario.get(itemAvaliado).doubleValue())
				* qntVolAvaliaramItem1Item2[itemAvaliado][itemX];
	}

	private void calculaMedias(double[] qntAvaliacoesItem) {
		double media;

		for (int item : predicoes.keySet()) {
			media = predicoes.get(item).doubleValue() / qntAvaliacoesItem[item];
			predicoes.put(item, media);
		}
	}

	private List<Long> getIdsItensRecomendados() {

		List<Long> idItensRecomendados = new ArrayList<>();

		for (int item : predicoes.keySet()) {
			double avaliacao = predicoes.get(item).doubleValue();
			if (!Double.isNaN(avaliacao) && avaliacao >= new Double(3)) {
				idItensRecomendados.add(new Long(item));
			}

			System.out.println(item + " " + avaliacao);
		}

		return idItensRecomendados;
	}

	private boolean isItemNaoAvaliado(int i) {
		return !matrizAvaliacaoItemVoluntario.containsKey(i);
	}

	private void zeraMatrizPredicoes() {
		for (int aux = 1; aux <= quantidadeItensAvaliados; aux++) {
			predicoes.put(aux, new Double(0.0));
		}
	}

	public void getAvaliacoesFeitasPeloVoluntario(int voluntarioPredicoes) {
		try {

			FileInputStream fileInputStram = new FileInputStream(new File(pathArquivoAvaliacoes));
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStram);
			DataInputStream dataInputStram = new DataInputStream(bufferedInputStream);

			StringTokenizer linhaFormatada;
			int voluntarioTemp;

			while (dataInputStram.available() != 0) {

				linhaFormatada = retiraVirgula(getProximaLinha(dataInputStram));
				voluntarioTemp = getProximoInt(linhaFormatada);

				while (voluntarioPredicoes == voluntarioTemp) {

					matrizAvaliacaoItemVoluntario.put(getProximoInt(linhaFormatada), getProximoDouble(linhaFormatada));

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

	public void leArquivoDiffMediaAvaliacoes() {
		/*
		 * Le o arquivo com as diferencas media de avaliacoes e salva na
		 * diffMediaAvaliacoesItem e salva a qnt de voluntarios que avaliaram o
		 * item em qntVolAvaliaramItem
		 * 
		 */
		try {

			FileInputStream fileInputStream = new FileInputStream(new File(pathArquivoDiferencas));
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

			String linha = getProximaLinha(dataInputStream);
			StringTokenizer token = getTab(linha);

			zerandoMatrizes(token);

			while (dataInputStream.available() != 0) {

				linha = getProximaLinha(dataInputStream);
				token = getTab(linha);

				int item1 = getProximoInt(token);
				int item2 = getProximoInt(token);

				diffMediaAvaliacoesItem1Item2[item1][item2] = getProximoDouble(token);

				linha = getProximaLinha(dataInputStream);
				token = getTab(linha);

				item1 = getProximoInt(token);
				item2 = getProximoInt(token);

				qntVolAvaliaramItem1Item2[item1][item2] = getProximoDouble(token);
			}

			fileInputStream.close();
			bufferedInputStream.close();
			dataInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void zerandoMatrizes(StringTokenizer token) {
		quantidadeItensAvaliados = getProximoInt(token);

		diffMediaAvaliacoesItem1Item2 = new Double[quantidadeItensAvaliados + 1][quantidadeItensAvaliados + 1];
		qntVolAvaliaramItem1Item2 = new Double[quantidadeItensAvaliados + 1][quantidadeItensAvaliados + 1];

		for (int item1 = 1; item1 <= quantidadeItensAvaliados; item1++) {
			for (int item2 = 1; item2 <= quantidadeItensAvaliados; item2++) {
				diffMediaAvaliacoesItem1Item2[item1][item2] = new Double("0");
				qntVolAvaliaramItem1Item2[item1][item2] = new Double("0");
			}
		}
	}

	private StringTokenizer getTab(String linha) {
		return new StringTokenizer(linha, "\t");
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
