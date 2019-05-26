package domain.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import domain.models.Uf;


@Named
@RequestScoped
public class UfRepository implements Serializable {

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

	public List<Uf> pesquisar(String textoDePesquisa){
		return dao.listaTodos().stream()
				.filter(x -> x.getNome().contains(textoDePesquisa)
						|| x.getSigla().contains(textoDePesquisa))
				.collect(Collectors.toList());
	}

	public List<Uf> listaTodos() {
		return dao.listaTodos();
	}
}
