package main.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import main.domain.Cidade;


@Named
@RequestScoped
public class EmpresaDao implements Serializable {

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

    public void remove(Cidade cidade) {
        dao.remove(cidade);
    }

    public void atualiza(Cidade cidade) {
        dao.atualiza(cidade);
    }

    public Cidade buscaPorId(Long id) {
        return dao.buscaPorId(id);
    }

    public List<Cidade> listaTodosPaginada(int firstResult, int maxResults) {
        return dao.listaTodosPaginada(firstResult, maxResults);
    }
}
