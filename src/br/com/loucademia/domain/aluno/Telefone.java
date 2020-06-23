package br.com.loucademia.domain.aluno;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;


@Embeddable
public class Telefone implements Serializable {
	public enum TipoTelefone {
		Celular,
		Residencial,
		Comercial,
		Fax;
	}
	
	@Column(name = "Telefone", nullable = false, length = 11)
	private Integer telefone;
	@Enumerated
	@Column(name = "Tipo_telefone", nullable = false, length = 1)
	private TipoTelefone tipo = TipoTelefone.Celular;

	public Integer getTelefone() {
		return telefone;
	}
	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
	public TipoTelefone getTipo() {
		return tipo;
	}
	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Telefone [telefone=" + telefone + ", tipo=" + tipo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Telefone other = (Telefone) obj;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
}
