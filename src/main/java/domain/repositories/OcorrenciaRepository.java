package domain.repositories;

import domain.models.Ocorrencia;

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

    public void remove(Ocorrencia ocorrencia) {
        dao.remove(ocorrencia);
    }

    public void atualiza(Ocorrencia ocorrencia) {
        dao.atualiza(ocorrencia);
    }

    public List<Ocorrencia> listaTodos() {
        return dao.listaTodos();
    }

    public List<Ocorrencia> pesquisar(String textoDePesquisa){
        return dao.listaTodos().stream()
                .filter(x -> x.getNome().contains(textoDePesquisa))
                .collect(Collectors.toList());
    }
}
