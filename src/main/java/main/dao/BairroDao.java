package main.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import main.domain.Bairro;


@Named
@RequestScoped
public class BairroDao implements Serializable {

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

	public void remove(Bairro bairro) {
		dao.remove(bairro);
	}

	public void atualiza(Bairro bairro) {
		dao.atualiza(bairro);
	}

	public Bairro buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public List<Bairro> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<Bairro> listaTodos() {
		return dao.listaTodos();
	}
}
