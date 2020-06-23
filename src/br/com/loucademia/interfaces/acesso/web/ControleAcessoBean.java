package br.com.loucademia.interfaces.acesso.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.loucademia.application.service.AcessoServive;
import br.com.loucademia.application.util.ValidaException;
import br.com.loucademia.domain.acesso.Acesso.TipoAcesso;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable {
	@EJB
	private AcessoServive acessoServive;
	@Inject
	private FacesContext facesContext;
	
	private String codigo;
	private String rg;

	public String registrarAcesso() {
		TipoAcesso tipoAcesso;
		try {
			tipoAcesso = acessoServive.registrarAcesso(codigo, rg);			
		} catch (ValidaException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		String msg;
		if(tipoAcesso == TipoAcesso.Entrada) {
			msg = "Entrada registrada";
		} else {
			msg = "Saída registrada";
		}
		facesContext.addMessage(null, new FacesMessage(msg));
		return null;
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

}
