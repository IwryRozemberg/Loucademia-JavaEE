package br.com.loucademia.application.service;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.loucademia.application.util.StringsUtils;
import br.com.loucademia.application.util.ValidaException;
import br.com.loucademia.domain.acesso.Acesso;
import br.com.loucademia.domain.acesso.Acesso.TipoAcesso;
import br.com.loucademia.domain.acesso.AcessoRepository;
import br.com.loucademia.domain.aluno.Aluno;
import br.com.loucademia.domain.aluno.AlunoRepository;

@Stateless
public class AcessoServive {

	@EJB
	private AcessoRepository acessoRepository;

	@EJB
	private AlunoRepository alunoRepository;

	public TipoAcesso registrarAcesso(String codigo, String rg) {
		String msg;
		if (StringsUtils.isEmpty(codigo) && StringsUtils.isEmpty(rg)) {
			throw new ValidaException("É necesssário informar algum parametro para acesso");
		}
		Aluno aluno;
		if (StringsUtils.isEmpty(codigo)) {
			aluno = alunoRepository.findByRg(rg);
			msg = "RG ";
		} else {
			aluno = alunoRepository.findById(codigo);
			msg = "Código ";
		}

		if (aluno == null) {
			throw new ValidaException(msg + "Não encontrado");
		}

		Acesso ultimoAcesso = acessoRepository.findLastAcesso(aluno);
		Acesso novoAcesso = new Acesso();
		novoAcesso.setAluno(aluno);
		if (ultimoAcesso == null) {
			novoAcesso.registrarAcesso(TipoAcesso.Entrada);
		} else if (ultimoAcesso.getTipoAcesso() == TipoAcesso.Entrada) {
			novoAcesso.registrarAcesso(TipoAcesso.Saida);
		} else {
			novoAcesso.registrarAcesso(TipoAcesso.Entrada);
		}
		acessoRepository.store(novoAcesso);

		return novoAcesso.getTipoAcesso();
	}

	public List<Acesso> listAcessos(String codigo, LocalDate dataInicio, LocalDate dataTermino){
		if((dataInicio != null && dataTermino == null) || (dataInicio == null && dataTermino != null)) {
			throw new ValidaException("A data inicial e final devem ser preenchidas.");
		} else if((dataInicio != null && dataTermino != null)) {
			if(dataInicio.isAfter(dataTermino)) {
				throw new ValidaException("A data final deve ser maior ou igual a data inicial");
			}
		}
	return acessoRepository.listAcesso(codigo,dataInicio,dataTermino);
}}
