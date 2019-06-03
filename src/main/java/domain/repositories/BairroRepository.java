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

import domain.models.Bairro;
import domain.models.Bairro;
import infrastructure.persistence.DAO;
import infrastructure.persistence.Repository;


@Named
@RequestScoped
public class BairroRepository implements Serializable, Repository<Bairro> {

	private static final long serialVersionUID = 1L;

	private DAO<Bairro> dao;
	
	@Inject
	EntityManager em;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Bairro>(this.em, Bairro.class);
	}

	public void adiciona(Bairro bairro) {
		dao.adiciona(bairro);
	}

	public void atualiza(Bairro bairro) {
		dao.atualiza(bairro);
	}

	public void remove(Bairro bairro) {
		dao.remove(bairro);
	}

	public List<Bairro> listaTodos() {
		return dao.listaTodos();
	}


	public List<Bairro> pesquisar(String textoDePesquisa) {
		String jpqlBairro = "select u from Bairro u where u.nome like :pNome";
		TypedQuery<Bairro> queryBairro = this.em.createQuery(jpqlBairro, Bairro.class);
		queryBairro.setParameter("pNome", "%" + textoDePesquisa + "%");
		try {
			return queryBairro.getResultList();
		} catch(NoResultException ex) {
			System.out.println(this.em);
		}
		return new ArrayList<>();
	}
}
