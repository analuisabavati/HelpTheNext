package br.com.helpthenext.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_doacao")
@Entity
@NamedQueries({
	 
	@NamedQuery(name = "DoacaoEntity.findAll",query= "SELECT p FROM DoacaoEntity p")
 
})
public class DoacaoEntity  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_evento")
	private Long id;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "foto")
	private byte[] foto;
	

}
