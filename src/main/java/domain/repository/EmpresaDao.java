package domain.repository;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import domain.models.Empresa;


@Named
@RequestScoped
public class EmpresaDao implements Serializable {

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

    public void remove(Empresa empresa) {
        dao.remove(empresa);
    }

    public void atualiza(Empresa empresa) {
        dao.atualiza(empresa);
    }

    public Empresa buscaPorId(Long id) {
        return dao.buscaPorId(id);
    }

    public List<Empresa> listaTodosPaginada(int firstResult, int maxResults) {
        return dao.listaTodosPaginada(firstResult, maxResults);
    }

    public List<Empresa> listaTodos() {
        return dao.listaTodos();
    }
}
