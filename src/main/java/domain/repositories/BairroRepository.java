package domain.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import domain.models.Bairro;


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

	public List<Bairro> pesquisar(String textoDePesquisa){
		return dao.listaTodos().stream()
				.filter(x -> x.getNome().contains(textoDePesquisa))
				.collect(Collectors.toList());
	}
}
