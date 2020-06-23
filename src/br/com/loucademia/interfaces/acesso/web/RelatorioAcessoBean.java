package br.com.loucademia.interfaces.acesso.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketbox.util.StringUtil;

import br.com.loucademia.application.service.AcessoServive;
import br.com.loucademia.application.util.ValidaException;
import br.com.loucademia.domain.acesso.Acesso;

@Named
@RequestScoped
public class RelatorioAcessoBean implements Serializable {
	@EJB
	private AcessoServive acessoService;

	@Inject
	private FacesContext facesContext;

	@Inject
	@RequestParameterMap
	private Map<String, String> requestParamsMap;

	private String codigo;
	private LocalDate dataInicial;
	private LocalDate dataFinal;
	private List<Acesso> listAcesso;

	public void check() {
		String clear = requestParamsMap.get("clear");
		if(StringUtil.isNullOrEmpty(clear) && Boolean.valueOf(clear)) {
			codigo = null;
			dataInicial = null;
			dataFinal = null;
			listAcesso = null;	
		}
	}
		
	
	public String gerarRelatorio() {
		try {
			listAcesso = acessoService.listAcessos(codigo, dataInicial, dataFinal);
		} catch (ValidaException e) {
			facesContext.addMessage(codigo, new FacesMessage(e.getMessage()));
		}
		return null;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<Acesso> getListAcesso() {
		return listAcesso;
	}
}
