package massaDados;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.helpthenext.enums.Causas;
import br.com.helpthenext.enums.TipoUsuario;
import br.com.helpthenext.repository.ArrayList;
import br.com.helpthenext.repository.Integer;
import br.com.helpthenext.repository.List;
import br.com.helpthenext.repository.ONGRepository;
import br.com.helpthenext.repository.String;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.ONGEntity;
import br.com.helpthenext.repository.entity.UsuarioEntity;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.uteis.Uteis;

public class InsereONGs {

	@Inject
	Gerador gerador;

	@Inject
	ONGRepository ongRepository;

	public void insereVolunarios() {
		int qntVoluntarios = 100;

		for (int i = 1; i <= qntVoluntarios; i++) {
			UsuarioEntity usuario = criaUsuario(i);
			 ONGEntity ong = criaONG(i, usuario);
			persisteUsuarioAndONG(usuario, ong);
		}
	}
	
	private ONGEntity criaONG(int i, UsuarioEntity usuario) {
		ONGEntity ongEntity = new ONGEntity();
		ongEntity.setDataCadastro(LocalDateTime.now());
		ongEntity.setEmail("ana_bavati@hotmail.com");
		ongEntity.setNomeONG("ONG" +i);
		ongEntity.setNomeResponsavel("Responsavel ong "+i);
		ongEntity.setTelefone(gerador.gerarTelefone());
		ongEntity.setDescricao("Descricao sobre a ong");

		
		ongEntity.setCausas(getCausas());

		ongEntity.setWebsite(ongModel.getWebsite());
		ongEntity.setFacebook(ongModel.getFacebook());
		ongEntity.setNumero(ongModel.getNumero());
		ongEntity.setComplemento(ongModel.getComplemento());
		ongEntity.setCep(gerador.gerarCEP());
		ongEntity.setCidade("Campinas");
		ongEntity.setEstado("SP");
		
		return ongEntity;
	}

	private void persisteUsuarioAndONG(UsuarioEntity u, ONGEntity ong) {
		EntityManager entityManager = Uteis.JpaEntityManager();
		entityManager.persist(u);
		entityManager.persist(ong);
	}

	public UsuarioEntity criaUsuario(int i) {
		UsuarioEntity usuario = new UsuarioEntity();

		usuario.setUsuario("ONG" + i);
		usuario.setTipoUsuario(TipoUsuario.ONG);
		usuario.setSenha(gerador.gerarSenha());

		return usuario;
	}
	
	private List<Causas> getCausas() {

		int qnt = gerador.numAleatorio(1, 3);

		List<Integer> numeros = new ArrayList<>();

		for (int i = 0; i < qnt; i++) {
			int n = gerador.numAleatorio(0, 11);
			if (!numeros.contains(n)) {
				numeros.add(n);
			}
		}

		List<Causas> causas = new ArrayList<>();
		for (Integer integer : numeros) {
			causas.add(Causas.values()[integer]);
		}

		return causas;
	}
	
}
