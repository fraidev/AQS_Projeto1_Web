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
import domain.models.Fiscalizacao;


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
        em.clear();
    }

    public void atualiza(Fiscalizacao fiscalizacao) {
        dao.atualiza(fiscalizacao);
        em.clear();
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

    public List<Fiscalizacao> pesquisar(String textoDePesquisa){
        return dao.listaTodos().stream()
                .filter(x -> x.getEmpresa().getRazaoSocial().contains(textoDePesquisa)
                        || x.getEmpresa().getCnpj().contains(textoDePesquisa))
                .limit(100)
                .collect(Collectors.toList());
    }
}
