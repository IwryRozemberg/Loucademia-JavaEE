package br.com.loucademia.domain.aluno;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class EstadoRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Estado> estadoList(){
		return entityManager.createQuery("SELECT e FROM Estado e ORDER BY e.nome", Estado.class).getResultList();
	}

}
