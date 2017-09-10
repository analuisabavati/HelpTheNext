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

	Double diffMediaAvaliacoesItem[][];
	Double qntVolAvaliaramItem[][];

	int quantidadeItensAvaliados;

	String pathArquivoAvaliacoes;
	String pathArquivoDiferencas;

	public static void main(String args[]) {
		System.out.println("\n[SlopOne] -  Iniciando Predicoes  -");
		String pathArqDiferencas1 = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\diff.txt";
		String pathArqAvaliacoes1 = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\avaliacoes.txt";

	//	long inicio = System.currentTimeMillis();
		Predicoes p = new Predicoes();
		p.calculaPredicoes(2, pathArqAvaliacoes1, pathArqDiferencas1);
//		long fim = System.currentTimeMillis();

	//	System.out.println("\n[SlopOne] Tempo de execução: " + (fim - inicio) + " ms.");
	}

	public List<Long> calculaPredicoes(int idVoluntario, String pathArquivoAvaliacoes, String pathArquivoDiferencas) {

		this.pathArquivoAvaliacoes = pathArquivoAvaliacoes;
		this.pathArquivoDiferencas = pathArquivoDiferencas;

		getAvaliacoesFeitaPeloVoluntario(idVoluntario);
		leArquivoDiffMediaAvaliacoes();
		zeraMatrizPredicoes();

		double qntAvaliacoesItem[] = new double[quantidadeItensAvaliados + 1];

		for (int itemAvaliado : matrizAvaliacaoItemVoluntario.keySet()) {
			
			for (int itemX = 1; itemX <= quantidadeItensAvaliados; itemX++) {
				
				if (itemX != itemAvaliado && isItemNaoAvaliado(itemX)) {
					double valorPredicao = 0;
					
					/*
					 * Calcula a nota que o voluntario daria ao itemX baseado no itemAvaliado
					 * 
					 */

					if (itemX >= itemAvaliado) {
						valorPredicao = qntVolAvaliaramItem[itemAvaliado][itemX]
								* (-1 * diffMediaAvaliacoesItem[itemAvaliado][itemX]
										+ matrizAvaliacaoItemVoluntario.get(itemAvaliado).doubleValue());
					} else {
						valorPredicao = qntVolAvaliaramItem[itemAvaliado][itemX]
								* (diffMediaAvaliacoesItem[itemAvaliado][itemX]
										+ matrizAvaliacaoItemVoluntario.get(itemAvaliado).doubleValue());
					}

					qntAvaliacoesItem[itemX] = qntAvaliacoesItem[itemX] + qntVolAvaliaramItem[itemAvaliado][itemX];

					predicoes.put(itemX, predicoes.get(itemX).doubleValue() + valorPredicao);
				}
			}
		}

		calculaMedias(qntAvaliacoesItem);
		
		return getIdsItensRecomendados();
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

	public void getAvaliacoesFeitaPeloVoluntario(int voluntarioPredicoes) {
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

			quantidadeItensAvaliados = getProximoInt(token);

			diffMediaAvaliacoesItem = new Double[quantidadeItensAvaliados + 1][quantidadeItensAvaliados + 1];
			qntVolAvaliaramItem = new Double[quantidadeItensAvaliados + 1][quantidadeItensAvaliados + 1];

			for (int item1 = 1; item1 <= quantidadeItensAvaliados; item1++) {
				for (int item2 = 1; item2 <= quantidadeItensAvaliados; item2++) {
					diffMediaAvaliacoesItem[item1][item2] = new Double("0");
					qntVolAvaliaramItem[item1][item2] = new Double("0");
				}
			}

			while (dataInputStream.available() != 0) {

				linha = getProximaLinha(dataInputStream);
				token = getTab(linha);

				int item1 = getProximoInt(token);
				int item2 = getProximoInt(token);

				diffMediaAvaliacoesItem[item1][item2] = getProximoDouble(token);

				linha = getProximaLinha(dataInputStream);
				token = getTab(linha);

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
