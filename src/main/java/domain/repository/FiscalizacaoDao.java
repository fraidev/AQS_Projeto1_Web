package domain.repository;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import domain.models.Fiscalizacao;


@Named
@RequestScoped
public class FiscalizacaoDao implements Serializable {

    private static final long serialVersionUID = 1L;

    private DAO<Fiscalizacao> dao;

    @Inject
    EntityManager em;

    @PostConstruct
    void init() {
        this.dao = new DAO<Fiscalizacao>(this.em, Fiscalizacao.class);
    }

    public void adiciona(Fiscalizacao fiscalizacao) {
        dao.adiciona(fiscalizacao);
    }

    public void remove(Fiscalizacao fiscalizacao) {
        dao.remove(fiscalizacao);
    }

    public void atualiza(Fiscalizacao fiscalizacao) {
        dao.atualiza(fiscalizacao);
    }

    public Fiscalizacao buscaPorId(Long id) {
        return dao.buscaPorId(id);
    }

    public List<Fiscalizacao> listaTodosPaginada(int firstResult, int maxResults) {
        return dao.listaTodosPaginada(firstResult, maxResults);
    }

    public List<Fiscalizacao> listaTodos() {
        return dao.listaTodos();
    }
}
