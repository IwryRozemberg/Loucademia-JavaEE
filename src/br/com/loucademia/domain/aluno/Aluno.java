package br.com.loucademia.domain.aluno;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.loucademia.application.util.StringsUtils;


@Entity
@Table(name = "Aluno")
public class Aluno implements Serializable {
	public enum Situacao {
		Ativo,
		Inativo,
		Pendente;
	};

	public enum Sexo {
		Masculino,
		Feminimo;
	}

	@Id	
	@Column(name = "Codigo", nullable = false, length = 8)
	private String codigo;
	@Column(name = "Nome", nullable = false, length = 64)
	private String nome;
	@Column(name = "RG", nullable = true, length = 16)
	private String rg;
	@Enumerated
	@Column(name = "Sexo", nullable = false, length = 1)
	private Sexo sexo;
	@Column(name = "Data_nascimento", nullable = false)
	private LocalDate dataNascimento;
	@Enumerated
	@Column(name = "Situacao", nullable = false, length = 1)
	private Situacao situacao;
	@Column(name = "Email", nullable = true, length = 64)
	private String email;
	@Embedded
	private Endereco endereco = new Endereco();	
	@Embedded
	private Telefone telefone = new Telefone();
	

	public void geraMatricula(String numAtual) {
		Year anoAtual = Year.now();
		int sequencial = 1;
		if(numAtual == null) {
			numAtual = anoAtual + StringsUtils.letfZeros(0, 4);
		}
		sequencial += Integer.parseInt(numAtual.substring(4));
		this.codigo = anoAtual + StringsUtils.letfZeros(sequencial, 4);
	}	

	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return "Aluno [codigo=" + codigo + ", nome=" + nome + ", sexo=" + sexo + ", dataNascimento=" + dataNascimento
				+ ", situacao=" + situacao + ", email=" + email + ", endereco=" + endereco + ", telefone=" + telefone
				+ "]";
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
		Aluno other = (Aluno) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
