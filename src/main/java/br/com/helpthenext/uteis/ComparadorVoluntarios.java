package br.com.helpthenext.uteis;

import java.util.Comparator;

import br.com.helpthenext.model.VoluntarioModel;

public class ComparadorVoluntarios implements Comparator<VoluntarioModel> {
	public int compare(VoluntarioModel o1, VoluntarioModel o2) {
		if (o1.getPontos() < o2.getPontos())
			return -1;
		else if (o1.getPontos() > o2.getPontos())
			return +1;
		else
			return 0;
	}

}
