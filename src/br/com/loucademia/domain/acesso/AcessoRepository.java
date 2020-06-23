package br.com.loucademia.domain.acesso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.picketbox.util.StringUtil;

import br.com.loucademia.domain.aluno.Aluno;

@Stateless
public class AcessoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Acesso findLastAcesso(Aluno aluno) {
		try {
			return entityManager
					.createQuery("SELECT a FROM Acesso a WHERE a.aluno.codigo = :codigo ORDER BY a.dataAcesso DESC",
							Acesso.class)
					.setParameter("codigo", aluno.getCodigo()).setMaxResults(1).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public void store(Acesso acesso) {
		entityManager.persist(acesso);
	}

	public List<Acesso> listAcesso(String codigo, LocalDate dataInicial, LocalDate dataFinal) {
		StringBuilder jpql = new StringBuilder("SELECT a FROM Acesso a ");
		if (!StringUtil.isNullOrEmpty(codigo)) {
			jpql.append("WHERE a.aluno.codigo = :codigo "); 
		}
		if (dataInicial != null && dataFinal != null) {
			if(StringUtil.isNullOrEmpty(codigo)) {
				jpql.append("WHERE a.dataAcesso >= :dataInicial AND a.dataAcesso <= :dataFinal ");				
			} else {
				jpql.append("AND a.dataAcesso >= :dataInicial AND a.dataAcesso <= :dataFinal ");				
			}
		}
		jpql.append(" ORDER BY dataAcesso ");

		TypedQuery<Acesso> query = entityManager.createQuery(jpql.toString(), Acesso.class);
		if (!StringUtil.isNullOrEmpty(codigo)) {
			query.setParameter("codigo", codigo);
		}
		if (dataInicial != null && dataFinal != null) {
			LocalDateTime ltinit = LocalDateTime.of(dataInicial, LocalTime.of(0, 0));
			LocalDateTime ltfim = LocalDateTime.of(dataFinal, LocalTime.of(23, 59, 59));
			query.setParameter("dataInicial", ltinit);
			query.setParameter("dataFinal", ltfim);
		}
		
		return query.getResultList();
	}
}
