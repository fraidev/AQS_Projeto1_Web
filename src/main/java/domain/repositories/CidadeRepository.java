package domain.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import domain.models.Cidade;
import infrastructure.persistence.DAO;
import infrastructure.persistence.Repository;


@Named
@RequestScoped
public class CidadeRepository implements Serializable, Repository<Cidade> {

    private static final long serialVersionUID = 1L;

    private DAO<Cidade> dao;

    @Inject
    EntityManager em;

    @PostConstruct
    void init() {
        this.dao = new DAO<Cidade>(this.em, Cidade.class);
    }

    public void adiciona(Cidade cidade) {
        dao.adiciona(cidade);
    }

    public void atualiza(Cidade cidade) {
        dao.atualiza(cidade);
    }

    public void remove(Cidade cidade) {
        dao.remove(cidade);
    }

    public List<Cidade> listaTodos() {
        return dao.listaTodos();
    }

    public List<Cidade> pesquisar(String textoDePesquisa){
        return dao.listaTodos().stream()
                .filter(x -> x.getNome().contains(textoDePesquisa))
                .collect(Collectors.toList());
    }
}
