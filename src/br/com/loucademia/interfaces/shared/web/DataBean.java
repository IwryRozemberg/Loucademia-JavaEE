package br.com.loucademia.interfaces.shared.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import java.util.List;

import br.com.loucademia.application.service.DataService;
import br.com.loucademia.domain.aluno.Aluno.Sexo;
import br.com.loucademia.domain.aluno.Aluno.Situacao;
import br.com.loucademia.domain.aluno.Estado;

@Named
@ApplicationScoped
public class DataBean implements Serializable {
	
	@EJB
	private DataService dataService;
	
	public List<Estado> getEstadoList() {
		return dataService.estadoList();
	}
	
	public Sexo[] getSexoList() {
		return dataService.getSexoList();
	}
	
	public Situacao[] getSituacaoList() {
		return dataService.getSituacaoList();
	}
}
