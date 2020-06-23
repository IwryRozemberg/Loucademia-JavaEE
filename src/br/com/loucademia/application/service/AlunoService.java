package br.com.loucademia.application.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.loucademia.application.util.StringsUtils;
import br.com.loucademia.application.util.Validacao;
import br.com.loucademia.domain.aluno.Aluno;
import br.com.loucademia.domain.aluno.Aluno.Situacao;
import br.com.loucademia.domain.aluno.AlunoRepository;

@Stateless
public class AlunoService {
	@EJB
	private AlunoRepository alunoRepository;
	
	public void createOrUpdate(Aluno aluno) {
		if(StringsUtils.isEmpty(aluno.getCodigo())) {
			create(aluno);
		} else {
			update(aluno);
		}
	}
	
	private void create(Aluno aluno) {
		Validacao.assertNotEmpty(aluno);

		String numAtual = alunoRepository.getMaxCodigo();
		aluno.geraMatricula(numAtual);
		alunoRepository.store(aluno);
	}
	
	private void update(Aluno aluno) {
		Validacao.assertNotEmpty(aluno);
		Validacao.assertNotEmpty(aluno.getCodigo());		
		alunoRepository.update(aluno);
	}
	
	public Aluno findByCodigo(String codigo) {
		return alunoRepository.findById(codigo);
	}
	
	public List<Aluno> listarAlunos(String codigo, String nome, String rg, Integer telefone, Situacao situacao) {
		return alunoRepository.listAluno(codigo, nome, rg, telefone, situacao);
	}

	public void delete(String codigo) {
		alunoRepository.delete(codigo);
		
	}
}
