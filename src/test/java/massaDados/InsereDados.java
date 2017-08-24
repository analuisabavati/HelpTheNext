package massaDados;

import javax.inject.Inject;

public class InsereDados {

	@Inject
	InsereVoluntarios insere;
	
	public void insereDados() {
		insere.insereVolunarios();
	}
}
