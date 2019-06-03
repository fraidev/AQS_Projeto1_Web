package bean;

import bean.models.Status;
import domain.models.Ocorrencia;
import domain.repositories.OcorrenciaRepository;
import infrastructure.tx.Transacional;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class OcorrenciaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private OcorrenciaRepository ocorrenciaRepository;
    private Ocorrencia ocorrencia;
    private Ocorrencia selected;
    private List<Ocorrencia> ocorrencias = new ArrayList<>();
    private Status status = Status.pesquisando;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void solicitaIncluir() {
        this.ocorrencia = new Ocorrencia();
        status = Status.incluindo;
    }

    public void solicitaAlterar() {
        this.ocorrencia = selected;
        status = Status.alterando;
    }

    public void pesquisar() {
        this.ocorrencias = ocorrenciaRepository.pesquisar(this.nome);
        this.selected = null;
    }

    public void cancelar() {
        status = Status.pesquisando;
    }
    public boolean isMostraEdicao() {
        return (status == Status.incluindo) || (status == Status.alterando);
    }
    public boolean isIncluindo() {
        return (status == Status.incluindo);
    }
    public boolean isAlterando() {
        return (status == Status.alterando);
    }
    public boolean isMostraPesquisa() {
        return (status == Status.pesquisando);
    }
    public boolean isDesabilitaAlteracao() {
        return (selected == null);
    }
    public boolean isDesabilitaExclusao() {
        return (selected == null);
    }

    public Ocorrencia getSelected() {
        return selected;
    }
    public void setSelected(Ocorrencia selected) {
        if (selected!= null) {
            System.out.println(selected.getId());
        }
        this.selected = selected;
    }
    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public Ocorrencia getOcorrencia() {
        return this.ocorrencia;
    }

    public List<Ocorrencia> getTodos(){
        return this.ocorrenciaRepository.listaTodos();
    }

    @Transacional
    public void confirmaInclusao(){
        this.ocorrenciaRepository.adiciona(ocorrencia);
        status = Status.pesquisando;
        this.ocorrencias = ocorrenciaRepository.listaTodos();
    }

    @Transacional
    public void confirmaAlteracao() {
        this.ocorrenciaRepository.atualiza(ocorrencia);
        this.selected = null;
        status = Status.pesquisando;
        this.ocorrencias = ocorrenciaRepository.listaTodos();
    }

    @Transacional
    public void solicitaExcluir() {
        this.ocorrenciaRepository.remove(selected);
        this.selected = null;
        this.ocorrencias = ocorrenciaRepository.listaTodos();
    }

    @PostConstruct
    public void init() {
        this.ocorrencias = ocorrenciaRepository.listaTodos();
        System.out.println("@PostConstruct OcorrenciaBean.init();");
    }
}
