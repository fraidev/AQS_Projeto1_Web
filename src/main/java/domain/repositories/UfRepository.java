package domain.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import domain.models.Uf;
import infrastructure.persistence.DAO;
import infrastructure.persistence.Repository;


@Named
@RequestScoped
public class UfRepository implements Serializable, Repository<Uf> {

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

	public void atualiza(Uf uf) {
		dao.atualiza(uf);
	}

	public void remove(Uf uf) {
		dao.remove(uf);
	}

	public List<Uf> pesquisar(String textoDePesquisa) {
		String jpqlUf = "select u from Uf u where u.nome like :pNome";
		TypedQuery<Uf> queryUf = this.em.createQuery(jpqlUf, Uf.class);
		queryUf.setParameter("pNome", "%" + textoDePesquisa + "%");
		try {
			return queryUf.getResultList();
		} catch(NoResultException ex) {
			System.out.println(this.em);
		}
		return new ArrayList<>();
	}

	public List<Uf> listaTodos() {
		return dao.listaTodos();
	}
}
