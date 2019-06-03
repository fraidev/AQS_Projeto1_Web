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

import domain.models.Cidade;
import domain.models.Uf;
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

    public List<Cidade> pesquisar(String textoDePesquisa) {
        String jpqlCidade = "select u from Cidade u where u.nome like :pNome";
        TypedQuery<Cidade> queryCidade = this.em.createQuery(jpqlCidade, Cidade.class);
        queryCidade.setParameter("pNome", "%" + textoDePesquisa + "%");
        try {
            return queryCidade.getResultList();
        } catch(NoResultException ex) {
            System.out.println(this.em);
        }
        return new ArrayList<>();
    }
}
