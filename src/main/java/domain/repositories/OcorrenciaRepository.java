package domain.repositories;

import domain.models.Ocorrencia;
import infrastructure.persistence.DAO;
import infrastructure.persistence.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OcorrenciaRepository implements Serializable, Repository<Ocorrencia> {
    private static final long serialVersionUID = 1L;
    private DAO<Ocorrencia> dao;

    @Inject
    EntityManager em;

    @PostConstruct
    void init() {
        this.dao = new DAO<Ocorrencia>(this.em, Ocorrencia.class);
    }

    public void adiciona(Ocorrencia ocorrencia) {
        dao.adiciona(ocorrencia);
    }

    public Ocorrencia buscaPorId(Long id){
        return dao.buscaPorId(id);
    }

    public void atualiza(Ocorrencia ocorrencia) {
        dao.atualiza(ocorrencia);
    }

    public void remove(Ocorrencia ocorrencia) {
        dao.remove(ocorrencia);
    }

    public List<Ocorrencia> listaTodosPaginada(int firstResult, int maxResults) {
        return dao.listaTodosPaginada(firstResult, maxResults);
    }

    public List<Ocorrencia> listaTodos() {
        return dao.listaTodos();
    }

    public List<Ocorrencia> pesquisar(String textoDePesquisa) {
        String jpqlOcorrencia = "select u from Ocorrencia u where u.nome like :pNome or u.codigo like :pNome";
        TypedQuery<Ocorrencia> queryOcorrencia = this.em.createQuery(jpqlOcorrencia, Ocorrencia.class);
        queryOcorrencia.setParameter("pNome", "%" + textoDePesquisa + "%");
        try {
            return queryOcorrencia.getResultList();
        } catch(NoResultException ex) {
            System.out.println(this.em);
        }
        return new ArrayList<>();
    }
}
