package br.com.helpthenext.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.helpthenext.enums.DiasSemana;
import br.com.helpthenext.enums.Periodos;

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
	
	@ElementCollection(targetClass = DiasSemana.class)
	@Enumerated(EnumType.ORDINAL)
	@CollectionTable(name = "doacao_dias")
	@Column(name = "dias_disponiveis")
	private List<DiasSemana> dias;

	@ElementCollection(targetClass = Periodos.class)
	@Enumerated(EnumType.ORDINAL)
	@CollectionTable(name = "doacao_periodos")
	@Column(name = "periodos_disponiveis")
	private List<Periodos> periodos;
	
	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;
	

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<DiasSemana> getDias() {
		return dias;
	}

	public void setDias(List<DiasSemana> dias) {
		this.dias = dias;
	}

	public List<Periodos> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodos> periodos) {
		this.periodos = periodos;
	}

	@ManyToOne
	@JoinColumn(name="id_voluntario")
	private VoluntarioEntity voluntarioEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public VoluntarioEntity getVoluntarioEntity() {
		return voluntarioEntity;
	}

	public void setVoluntarioEntity(VoluntarioEntity voluntarioEntity) {
		this.voluntarioEntity = voluntarioEntity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
