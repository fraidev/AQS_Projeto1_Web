package domain.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import domain.models.Fiscalizacao;
import domain.models.Fiscalizacao;
import infrastructure.persistence.DAO;
import infrastructure.persistence.Repository;


@Named
@RequestScoped
public class FiscalizacaoRepository implements Serializable, Repository<Fiscalizacao> {

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

    public void atualiza(Fiscalizacao fiscalizacao) {
        dao.atualiza(fiscalizacao);
    }

    public void remove(Fiscalizacao fiscalizacao) {
        dao.remove(fiscalizacao);
    }

    public List<Fiscalizacao> listaTodosPaginada(int firstResult, int maxResults) {
        return dao.listaTodosPaginada(firstResult, maxResults);
    }

    public List<Fiscalizacao> listaTodos() {
        return dao.listaTodos();
    }

    public List<Fiscalizacao> pesquisar(String textoDePesquisa) {
        String jpqlFiscalizacao = "select u from Fiscalizacao u where u.empresa.razaoSocial like :pNome " +
                "or u.empresa.cnpj like :pNome";
        TypedQuery<Fiscalizacao> queryFiscalizacao = this.em.createQuery(jpqlFiscalizacao, Fiscalizacao.class);
        queryFiscalizacao.setParameter("pNome", "%" + textoDePesquisa + "%");
        try {
            return queryFiscalizacao.setMaxResults(100).getResultList();
        } catch(NoResultException ex) {
            System.out.println(this.em);
        }
        return new ArrayList<>();
    }
}
