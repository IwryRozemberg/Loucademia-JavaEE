package br.com.loucademia.interfaces.aluno.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketbox.util.StringUtil;

import br.com.loucademia.application.service.AlunoService;
import br.com.loucademia.application.util.ValidaException;
import br.com.loucademia.domain.aluno.Aluno;
import br.com.loucademia.domain.aluno.Aluno.Situacao;

@Named
@SessionScoped
public class PesquisaAlunoBean implements Serializable {
	@Inject
	private FacesContext facesContext;
	@Inject
	@RequestParameterMap
	private Map<String, String> requestParamsMap;

	@EJB
	private AlunoService alunoService;

	private String codigo;
	private String nome;
	private String rg;
	private Integer telefone;
	private List<Aluno> resultadoPesquisa = new ArrayList<Aluno>();
	private Situacao situacao;

	public void check() {
		String clear = requestParamsMap.get("clear");
		if(StringUtil.isNullOrEmpty(clear) && Boolean.valueOf(clear)) {
			codigo = null;
			nome = null;
			rg = null;
			telefone = null;
			resultadoPesquisa = null;
			situacao = null;			
		}
	}
	
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Aluno> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(List<Aluno> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public String pesquisar() {
		try {
			resultadoPesquisa = alunoService.listarAlunos(codigo, nome, rg, telefone, situacao);
		} catch (ValidaException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}

	public String excluir(String codigo) {
		alunoService.delete(codigo);
		return pesquisar();
	}
}
