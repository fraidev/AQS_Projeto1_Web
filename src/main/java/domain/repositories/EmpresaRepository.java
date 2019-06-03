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

import domain.models.Empresa;
import domain.models.Empresa;
import infrastructure.persistence.DAO;
import infrastructure.persistence.Repository;


@Named
@RequestScoped
public class EmpresaRepository implements Serializable, Repository<Empresa> {

    private static final long serialVersionUID = 1L;

    private DAO<Empresa> dao;

    @Inject
    private EntityManager em;

    @PostConstruct
    void init() {
        this.dao = new DAO<Empresa>(this.em, Empresa.class);
    }

    public void adiciona(Empresa empresa) {
        dao.adiciona(empresa);
    }

    public void atualiza(Empresa empresa) {
        dao.atualiza(empresa);
    }

    public void remove(Empresa empresa) {
        dao.remove(empresa);
    }

    public List<Empresa> listaTodos() {
        return dao.listaTodos();
    }

    public List<Empresa> listaTodosPaginada(int firstResult, int maxResults) {
        return dao.listaTodosPaginada(firstResult, maxResults);
    }

    public List<Empresa> pesquisar(String textoDePesquisa) {
        String jpqlEmpresa = "select u from Empresa u where u.razaoSocial like :pNome";
        TypedQuery<Empresa> queryEmpresa = this.em.createQuery(jpqlEmpresa, Empresa.class);
        queryEmpresa.setParameter("pNome", "%" + textoDePesquisa + "%");
        try {
            return queryEmpresa.getResultList();
        } catch(NoResultException ex) {
            System.out.println(this.em);
        }
        return new ArrayList<>();
    }
}
