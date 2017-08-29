package br.com.helpthenext.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_avaliacao_evento")
@Entity
public class AvaliacaoEventoEntity  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_avaliacao_evento")
	private Long id;

	@Column(name = "id_evento")
	private EventoEntity evento;

	@Column(name = "id_voluntario")
	private VoluntarioEntity voluntario;
	
	@Column(name = "id_avaliacao_evento")
	private Long avaliacao;



}
