package br.com.loucademia.domain.acesso;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.loucademia.domain.aluno.Aluno;

@Entity
@Table(name = "ControleAcesso")
public class Acesso implements Serializable {
	public enum TipoAcesso {
		Entrada,
		Saida
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Codigo", nullable = false)
	private Integer codigo;
	@ManyToOne
	@JoinColumn(name = "Codigo_aluno", nullable = false)
	private Aluno aluno;
	@Column(name = "Data_acesso", nullable = false)
	private LocalDateTime dataAcesso;
	@Column(name = "Tipo_acesso", nullable = false)
	private TipoAcesso tipoAcesso;

	
	public TipoAcesso registrarAcesso(TipoAcesso tipoAcesso) {
		dataAcesso = LocalDateTime.now();
		this.tipoAcesso = tipoAcesso;
		return this.tipoAcesso;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public LocalDateTime getDataAcesso() {
		return dataAcesso;
	}

	public void setDataAcesso(LocalDateTime dataAcesso) {
		this.dataAcesso = dataAcesso;
	}

	public TipoAcesso getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(TipoAcesso tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

	@Override
	public String toString() {
		return "Acesso [codigo=" + codigo + ", aluno=" + aluno + ", dataAcesso=" + dataAcesso + ", tipoAcesso="
				+ tipoAcesso + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Acesso other = (Acesso) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
