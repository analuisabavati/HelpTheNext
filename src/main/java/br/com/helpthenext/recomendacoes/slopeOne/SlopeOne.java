package br.com.helpthenext.recomendacoes.slopeOne;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.inject.Inject;

import br.com.helpthenext.repository.AvaliacaoEventoRepository;
import br.com.helpthenext.repository.EventoRepository;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.AvaliacaoEventoEntity;

public class SlopeOne {

	@Inject
	private EventoRepository eventoRepository;

	@Inject
	private VoluntarioRepository voluntarioRepository;

	@Inject
	private AvaliacaoEventoRepository avaliacaoEventoRepository;

	Map<Integer, Map<Integer, Float>> matrizVoluntarioItemAvaliacao;
	float mediaAvaliacoes[][];
	int quantidadeAvaliacoes[][];

	int quantidadeItens = 0;

	String pathArquivoAvaliacoes;
	String pathArquivoDiferencas;

	public static void main(String args[]) {
		System.out.println("\n[SlopOne] -  Iniciando SlopeOne  -");
		String pathArqDiferencas1 = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\slope-intermidiary-output.txt";
		String pathArqAvaliacoes1 = "C:\\Users\\ana_b\\git\\HelpTheNext\\src\\main\\resources\\slopeOne\\ratings.dat";

		long inicio = System.currentTimeMillis();			
		new SlopeOne(pathArqAvaliacoes1, pathArqDiferencas1);
		long fim = System.currentTimeMillis();
	
		System.out.println("\n[SlopOne] Tempo de execução: " + (fim - inicio) + " ms.");
	}

	public SlopeOne(String pathArquivoAvaliacoes, String pathArquivoDiferencas) {

		this.pathArquivoAvaliacoes = pathArquivoAvaliacoes;
		this.pathArquivoDiferencas = pathArquivoDiferencas;

		leArquivoCriaMatrizVoluntarioItemAvaliacao();
		calculaMatrizDiferencas();
		escreveMatrizDiferencas();
	}

	@SuppressWarnings("deprecation")
	public void leArquivoCriaMatrizVoluntarioItemAvaliacao() {

		File file = new File(pathArquivoAvaliacoes);

		try {

			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

			String linha = dataInputStream.readLine();

			matrizVoluntarioItemAvaliacao = new HashMap<Integer, Map<Integer, Float>>();

			StringTokenizer t;
			int voluntario;
			int voluntarioTemp;
			int item;

			while (dataInputStream.available() != 0) {

				t = new StringTokenizer(linha, ",");
				voluntario = Integer.parseInt(t.nextToken());
				voluntarioTemp = voluntario;

				matrizVoluntarioItemAvaliacao.put(voluntario, new HashMap<Integer, Float>());

				while (voluntario == voluntarioTemp) {

					item = Integer.parseInt(t.nextToken());

					matrizVoluntarioItemAvaliacao.get(voluntario).put(item, Float.parseFloat(t.nextToken()));

					if (dataInputStream.available() != 0) {
						linha = dataInputStream.readLine();
						t = new StringTokenizer(linha, ",");
						voluntarioTemp = Integer.parseInt(t.nextToken());
					} else {
						voluntarioTemp = -1;
					}

					quantidadeItens = quantidadeItens < item ? item : quantidadeItens;
				}
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

	public void calculaMatrizDiferencas() {

		mediaAvaliacoes = new float[quantidadeItens + 1][quantidadeItens + 1];
		quantidadeAvaliacoes = new int[quantidadeItens + 1][quantidadeItens + 1];

		calculaDiferencas();
		calculaMedias();
	}

	private void calculaDiferencas() {
		for (int voluntario : matrizVoluntarioItemAvaliacao.keySet()) {
			for (int item1 : matrizVoluntarioItemAvaliacao.get(voluntario).keySet()) {
				for (int item2 : matrizVoluntarioItemAvaliacao.get(voluntario).keySet()) {
					mediaAvaliacoes[item1][item2] = mediaAvaliacoes[item1][item2]
							+ (matrizVoluntarioItemAvaliacao.get(voluntario).get(item1).floatValue()
									- (matrizVoluntarioItemAvaliacao.get(voluntario).get(item2).floatValue()));
					quantidadeAvaliacoes[item1][item2] = quantidadeAvaliacoes[item1][item2] + 1;
				}
			}
		}
	}

	private void calculaMedias() {
		for (int item1 = 1; item1 <= quantidadeItens; item1++) {
			for (int item2 = item1; item2 <= quantidadeItens; item2++) {
				if (quantidadeAvaliacoes[item1][item2] > 0) {
					mediaAvaliacoes[item1][item2] = mediaAvaliacoes[item1][item2] / quantidadeAvaliacoes[item1][item2];
				}
			}
		}
	}

	@SuppressWarnings("resource")
	private void escreveMatrizDiferencas() {
		try {
			FileOutputStream output = new FileOutputStream(pathArquivoDiferencas);

			output.write(String.valueOf(quantidadeItens).getBytes());
			output.write(String.valueOf("\n").getBytes());

			for (int item1 = 1; item1 <= quantidadeItens; item1++) {
				for (int item2 = item1; item2 <= quantidadeItens; item2++) {

					if (!Float.isNaN(mediaAvaliacoes[item1][item2])) {
						output.write(String.valueOf(item1).getBytes());
						output.write(String.valueOf("\t").getBytes());
						output.write(String.valueOf(item2).getBytes());
						output.write(String.valueOf("\t").getBytes());
						output.write(String.valueOf(mediaAvaliacoes[item1][item2]).getBytes());
						output.write(String.valueOf("\n").getBytes());

						output.write(String.valueOf(item1).getBytes());
						output.write(String.valueOf("\t").getBytes());
						output.write(String.valueOf(item2).getBytes());
						output.write(String.valueOf("\t").getBytes());
						output.write(String.valueOf(quantidadeAvaliacoes[item1][item2]).getBytes());
						output.write(String.valueOf("\n").getBytes());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Integer[][] getMatrizTodasAvaliacoes() {
		List<AvaliacaoEventoEntity> avaliacoes = avaliacaoEventoRepository.findAll();

		List<Long> idsEventos = eventoRepository.getIdsEventos();
		List<Long> idsVoluntarios = voluntarioRepository.getIdsVoluntarios();

		int qntVoluntarios = idsVoluntarios.size();
		int qntEventos = idsEventos.size();

		Integer[][] matrizAvaliacoes = new Integer[qntVoluntarios][qntEventos];

		Long idVoluntario;
		Long idEvento;
		for (int l = 0; l < qntVoluntarios; l++) {

			idVoluntario = idsVoluntarios.get(l);

			for (int c = 0; c < qntEventos; c++) {

				idEvento = idsEventos.get(c);

				matrizAvaliacoes[l][c] = getAvaliacao(idVoluntario, idEvento, avaliacoes);
			}
		}

		return matrizAvaliacoes;
	}

	private Integer getAvaliacao(Long idVol, Long idEvento, List<AvaliacaoEventoEntity> avaliacoes) {
		for (AvaliacaoEventoEntity avaliacaoEventoEntity : avaliacoes) {
			if (avaliacaoEventoEntity.getIdEvento().equals(idEvento)
					&& avaliacaoEventoEntity.getIdVoluntario().equals(idVol)) {
				return avaliacaoEventoEntity.getAvaliacao();
			}
		}

		return 0;
	}

}
