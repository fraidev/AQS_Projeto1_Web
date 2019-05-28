package domain.repositories;

import domain.models.Fiscal;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class FiscalRepository  implements Serializable, Repository<Fiscal> {

    private static final long serialVersionUID = 1L;

    private DAO<Fiscal> dao;

    @Inject
    EntityManager em;

    @PostConstruct
    void init() {
        this.dao = new DAO<Fiscal>(this.em, Fiscal.class);
    }

    public void adiciona(Fiscal fiscal) {
        dao.adiciona(fiscal);
    }

    public void atualiza(Fiscal fiscal) {
        dao.atualiza(fiscal);
    }

    public void remove(Fiscal fiscal) {
        dao.remove(fiscal);
    }

    public List<Fiscal> listaTodosPaginada(int firstResult, int maxResults) {
        return dao.listaTodosPaginada(firstResult, maxResults);
    }

    public List<Fiscal> listaTodos() {
        return dao.listaTodos();
    }

    public List<Fiscal> pesquisar(String textoDePesquisa){
        return dao.listaTodos().stream()
                .filter(x -> x.getNome().contains(textoDePesquisa))
                .limit(100)
                .collect(Collectors.toList());
    }
}
