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

	
//	public boolean existe(Uf uf) {
//		
//		TypedQuery<Uf> query = em.createQuery(
//				  " select u from Uf u "
//				+ " where u.email = :pLogin and u.senha = :pSenha", Uf.class);
//		
//		query.setParameter("pLogin", uf.getLogin());
//		query.setParameter("pSenha", uf.getSenha());
//		try {
//			@SuppressWarnings("unused")
//			Uf resultado = query.getSingleResult();
//			return true;
//		} catch (NoResultException ex) {
//			return false;
//		}
//	}
	
//	public Uf buscaUsuarioPelaAutenticacao(Uf uf) {
//		StringBuilder jpql = new StringBuilder();
//		jpql.append(" select u from Usuario u ");
//		jpql.append(" where ");
//		jpql.append("       u.login = :pLogin ");
//		jpql.append("   and u.senha = :pSenha ");
//		
//		TypedQuery<Uf> query = em.createQuery(jpql.toString() , Uf.class);
//		
//		query.setParameter("pLogin", uf.getLogin());
//		query.setParameter("pSenha", uf.getSenha());
//		try {
//			return query.getSingleResult();
//		} catch (NoResultException ex) {
//			return null;
//		}
//	}

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

//	public List<Uf> pesquisa(String login) {
//		StringBuilder jpql = new StringBuilder();
//		jpql.append(" select u ");
//		jpql.append(" 	 from Uf u ");
//		jpql.append(" where ");
//		jpql.append(" 	 u.login like :login");
//		jpql.append(" order by ");
//		jpql.append(" 	 u.login");
//		
//		TypedQuery<Uf> query = em.createQuery(jpql.toString(), Uf.class);
//		
//		query.setParameter("login", login + "%" );
//		
//		return query.getResultList();
//	}

}
