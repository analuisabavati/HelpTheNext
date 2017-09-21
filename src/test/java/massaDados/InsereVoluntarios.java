package massaDados;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.helpthenext.enums.Causas;
import br.com.helpthenext.enums.DiasSemana;
import br.com.helpthenext.enums.Habilidades;
import br.com.helpthenext.enums.Periodos;
import br.com.helpthenext.enums.TipoUsuario;
import br.com.helpthenext.repository.VoluntarioRepository;
import br.com.helpthenext.repository.entity.UsuarioEntity;
import br.com.helpthenext.repository.entity.VoluntarioEntity;
import br.com.helpthenext.uteis.Uteis;

public class InsereVoluntarios {

	@Inject
	Gerador gerador;

	@Inject
	VoluntarioRepository voluntarioRepository;

	@Inject
	UsuarioEntity usuarioEntity;

	public void insereVolunarios() {
		int qntVoluntarios = 100;

		for (int i = 1; i <= qntVoluntarios; i++) {
			UsuarioEntity usuario = criaUsuario(i);
			VoluntarioEntity vol = criaVoluntario(i, usuario);
			persisteUsuarioAndVoluntario(usuario, vol);
		}
	}

	private void persisteUsuarioAndVoluntario(UsuarioEntity u, VoluntarioEntity vol) {
		EntityManager entityManager = Uteis.JpaEntityManager();
		entityManager.persist(u);
		entityManager.persist(vol);
	}

	public UsuarioEntity criaUsuario(int i) {
		UsuarioEntity usuario = new UsuarioEntity();

		usuario.setUsuario("voluntario" + i);
		usuario.setTipoUsuario(TipoUsuario.VOLUNTARIO);
		usuario.setSenha(Gerador.gerarSenha());

		return usuario;
	}

	public VoluntarioEntity criaVoluntario(Integer i, UsuarioEntity usuario) {
		VoluntarioEntity voluntarioEntity = new VoluntarioEntity();

		voluntarioEntity.setNome("voluntario" + i);
		voluntarioEntity.setSobrenome("sobrenome" + i);
		voluntarioEntity.setSexo(i % 2 == 0 ? "M" : "F");
		voluntarioEntity.setCpf(Gerador.gerarCPF());
		voluntarioEntity.setDataNascimento(new Date());
		voluntarioEntity.setDataCadastro(LocalDateTime.now());
		voluntarioEntity.setEmail("ana_bavati@hotmail.com");

		voluntarioEntity.setEstado("SP");
		voluntarioEntity.setCidade("Campinas");
		voluntarioEntity.setCep(Gerador.gerarCEP());
		voluntarioEntity.setComplemento("-");
		voluntarioEntity.setNumero(new Long(i.toString()));
		voluntarioEntity.setRua("Rua " + i);
		voluntarioEntity.setTelefone(Gerador.gerarTelefone());
		voluntarioEntity.setRg(null);
		voluntarioEntity.setTrabalhoDistancia(i % 2 == 0 ? "S" : "N");

		voluntarioEntity.setCausas(getCausas());
		voluntarioEntity.setHabilidades(getHabilidades());
		voluntarioEntity.setDiasDisponiveis(getDias());
		voluntarioEntity.setPeriodosDisponiveis(getPeriodos());

		voluntarioEntity.setUsuarioEntity(usuario);

		return voluntarioEntity;
	}

	
	private List<Periodos> getPeriodos() {

		int qnt = Gerador.numAleatorio(1, 3);

		List<Integer> numeros = new ArrayList<>();

		for (int i = 0; i < qnt; i++) {
			int n = Gerador.numAleatorio(0, 2);
			if (!numeros.contains(n)) {
				numeros.add(n);
			}
		}

		List<Periodos> causas = new ArrayList<>();
		for (Integer integer : numeros) {
			causas.add(Periodos.values()[integer]);
		}

		return causas;
	}
	
	
	private List<DiasSemana> getDias() {

		int qnt = Gerador.numAleatorio(1, 6);

		List<Integer> numeros = new ArrayList<>();

		for (int i = 0; i < qnt; i++) {
			int n = Gerador.numAleatorio(0, 6);
			if (!numeros.contains(n)) {
				numeros.add(n);
			}
		}

		List<DiasSemana> causas = new ArrayList<>();
		for (Integer integer : numeros) {
			causas.add(DiasSemana.values()[integer]);
		}

		return causas;
	}
	
	private List<Habilidades> getHabilidades() {

		int qnt = Gerador.numAleatorio(1, 2);

		List<Integer> numeros = new ArrayList<>();

		for (int i = 0; i < qnt; i++) {
			int n = Gerador.numAleatorio(0, 11);
			if (!numeros.contains(n)) {
				numeros.add(n);
			}
		}

		List<Habilidades> causas = new ArrayList<>();
		for (Integer integer : numeros) {
			causas.add(Habilidades.values()[integer]);
		}

		return causas;
	}

	private List<Causas> getCausas() {

		int qnt = Gerador.numAleatorio(1, 3);

		List<Integer> numeros = new ArrayList<>();

		for (int i = 0; i < qnt; i++) {
			int n = Gerador.numAleatorio(0, 11);
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
