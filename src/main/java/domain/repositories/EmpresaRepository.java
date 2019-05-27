package domain.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import domain.models.Empresa;


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
        em.clear();
    }

    public void atualiza(Empresa empresa) {
        dao.atualiza(empresa);
        em.clear();
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

    public List<Empresa> pesquisar(String textoDePesquisa){
        return dao.listaTodos().stream()
                .filter(x -> x.getRazaoSocial().contains(textoDePesquisa))
                .limit(100)
                .collect(Collectors.toList());
    }
}
