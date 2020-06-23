package br.com.loucademia.domain.aluno;

import java.time.Year;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.loucademia.application.util.StringsUtils;
import br.com.loucademia.domain.aluno.Aluno.Situacao;

@Stateless
public class AlunoRepository {

	@PersistenceContext
	private EntityManager em;

	public void store(Aluno aluno) {
		em.persist(aluno);
	}

	public void update(Aluno aluno) {
		em.merge(aluno);
	}

	public void delete(String codigo) {
		Aluno aluno = findById(codigo);
		if (aluno != null) {
			em.remove(aluno);
		}
	}

	public Aluno findById(String codigo) {
		return em.find(Aluno.class, codigo);
	}

	public Aluno findByRg(String rg) {
		try {
			return em.createQuery("SELECT a FROM Aluno a WHERE a.rg = :rg", Aluno.class).setParameter("rg", rg)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public String getMaxCodigo() {
		return em.createQuery("SELECT MAX(a.codigo) FROM Aluno a WHERE a.codigo LIKE :ano", String.class)
				.setParameter("ano", Year.now() + "%").getSingleResult();
	}

	public List<Aluno> listAluno(String codigo, String nome, String rg, Integer telefone, Situacao situacao) {
		StringBuilder jpql = new StringBuilder("SELECT a FROM Aluno a WHERE a.situacao = :situacao ");

		if (!StringsUtils.isEmpty(codigo)) {
			jpql.append("AND a.codigo = :codigo ");
		}
		if (!StringsUtils.isEmpty(nome)) {
			jpql.append("AND a.nome LIKE :nome ");
		}
		if (!StringsUtils.isEmpty(rg)) {
			jpql.append("AND a.rg = :rg ");
		}
		if (telefone != null) {
			jpql.append("AND a.telefone.telefone = :telefone ");
		}

		TypedQuery<Aluno> result = em.createQuery(jpql.toString(), Aluno.class);
		result.setParameter("situacao", situacao);
		if (!StringsUtils.isEmpty(codigo)) {
			result.setParameter("codigo", codigo);
		}
		if (!StringsUtils.isEmpty(nome)) {
			result.setParameter("nome", "%" + nome + "%");
		}
		if (!StringsUtils.isEmpty(rg)) {
			result.setParameter("rg", rg);
		}
		if (telefone != null) {
			result.setParameter("telefone", telefone);
		}

		return result.getResultList();
	}
}
