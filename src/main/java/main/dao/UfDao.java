package main.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import main.conexao.JPAUtil;
import main.dao.DAO;
import main.domain.Uf;


@Named
@RequestScoped
public class UfDao implements Serializable {

	private static final long serialVersionUID = 1L;

	private DAO<Uf> dao;
	
	@Inject
	EntityManager em;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Uf>(this.em, Uf.class);
	}

	public void adiciona(Uf uf) {
		dao.adiciona(uf);
	}

	public void remove(Uf uf) {
		dao.remove(uf);
	}

	public void atualiza(Uf uf) {
		dao.atualiza(uf);
	}

	public Uf buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public List<Uf> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}
}
