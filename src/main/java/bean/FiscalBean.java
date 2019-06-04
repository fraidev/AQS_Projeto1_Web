package bean;

import bean.models.Status;
import domain.repositories.FiscalRepository;
import domain.models.Fiscal;
import domain.validators.CpfCnpjUtils;
import infrastructure.tx.Transacional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class FiscalBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private FiscalRepository fiscalRepository;
    private Fiscal fiscal;
    private Fiscal selected;
    private List<Fiscal> fiscais = new ArrayList<>();
    private Status status = Status.pesquisando;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void solicitaIncluir() {
        this.fiscal = new Fiscal();
        status = Status.incluindo;
    }

    public void solicitaAlterar() {
        this.fiscal = selected;
        status = Status.alterando;
    }

    public void pesquisar() {
        this.fiscais = fiscalRepository.pesquisar(this.nome);
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

    public Fiscal getSelected() {
        return selected;
    }
    public void setSelected(Fiscal selected) {
        if (selected!= null) {
            System.out.println(selected.getId());
        }
        this.selected = selected;
    }
    public List<Fiscal> getfiscais() {
        return fiscais;
    }

    public Fiscal getFiscal() {
        return this.fiscal;
    }

    public List<Fiscal> getTodos(){
        return this.fiscalRepository.listaTodos();
    }

    @Transacional
    public void confirmaInclusao(){
        if(isValid()){
            return;
        }
        this.fiscalRepository.adiciona(fiscal);
        status = Status.pesquisando;
        this.fiscais = fiscalRepository.listaTodos();
    }

    @Transacional
    public void confirmaAlteracao() {
        if(isValid()){
            return;
        }
        this.fiscalRepository.atualiza(fiscal);
        this.selected = null;
        status = Status.pesquisando;
        this.fiscais = fiscalRepository.listaTodos();
    }

    @Transacional
    public void solicitaExcluir() {
        this.fiscalRepository.remove(selected);
        this.selected = null;
        this.fiscais = fiscalRepository.listaTodos();
    }

    public boolean isValid(){
        if(CpfCnpjUtils.isCPF(this.fiscal.getCpf())){
            return true;
        }
        return false;
    }

    @PostConstruct
    public void init() {
        this.fiscais = fiscalRepository.listaTodos();
        System.out.println("@PostConstruct FiscalBean.init();");
    }
}
