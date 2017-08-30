package br.com.helpthenext.repository.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AvaliacaoEventoPk implements Serializable {

	private static final long serialVersionUID = -5704404103720777829L;
	
	private Long evento;
	
	private Long voluntario;

	public Long getEvento() {
		return evento;
	}

	public void setEvento(Long evento) {
		this.evento = evento;
	}

	public Long getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(Long voluntario) {
		this.voluntario = voluntario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		result = prime * result + ((voluntario == null) ? 0 : voluntario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvaliacaoEventoPk other = (AvaliacaoEventoPk) obj;
		if (evento == null) {
			if (other.evento != null)
				return false;
		} else if (!evento.equals(other.evento))
			return false;
		if (voluntario == null) {
			if (other.voluntario != null)
				return false;
		} else if (!voluntario.equals(other.voluntario))
			return false;
		return true;
	}
	
}
