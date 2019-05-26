package bean;

import domain.models.Fiscal;
import domain.repositories.FiscalRepository;
import domain.repositories.FiscalizacaoRepository;
import domain.models.Fiscalizacao;
import domain.models.Status;
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
public class FiscalizacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private FiscalizacaoRepository fiscalizacaoRepository;
	@Inject
	private FiscalRepository fiscalRepository;
	private Fiscalizacao fiscalizacao;
	private Fiscalizacao selected;
	private List<Fiscalizacao> fiscalizacaos = new ArrayList<>();
	private Status status = Status.pesquisando;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void solicitaIncluir() {
		this.fiscalizacao = new Fiscalizacao();
		status = Status.incluindo;
	}

	public void solicitaAlterar() {
		this.fiscalizacao = selected;
		status = Status.alterando;
	}

	public void pesquisar() {
		this.fiscalizacaos = fiscalizacaoRepository.pesquisar(this.nome);
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

	public Fiscalizacao getSelected() {
		return selected;
	}
	public void setSelected(Fiscalizacao selected) {
		if (selected!= null) {
			System.out.println(selected.getId());
		}
		this.selected = selected;
	}
	public List<Fiscalizacao> getFiscalizacaos() {
		return fiscalizacaos;
	}

	public Fiscalizacao getFiscalizacao() {
		return this.fiscalizacao;
	}

	public List<Fiscalizacao> getTodos(){
		return this.fiscalizacaoRepository.listaTodos();
	}

	public List<Fiscal> getTodosFiscais(){
		return this.fiscalRepository.listaTodos();
	}

	@Transacional
	public void confirmaInclusao(){
		this.fiscalizacaoRepository.adiciona(fiscalizacao);
		status = Status.pesquisando;
		this.fiscalizacaos = fiscalizacaoRepository.listaTodosPaginada(0, 100);
	}

	@Transacional
	public void confirmaAlteracao() {
		this.fiscalizacaoRepository.atualiza(fiscalizacao);
		this.selected = null;
		status = Status.pesquisando;
		this.fiscalizacaos = fiscalizacaoRepository.listaTodosPaginada(0, 100);
	}

	@Transacional
	public void solicitaExcluir() {
		this.fiscalizacaoRepository.remove(selected);
		this.selected = null;
		this.fiscalizacaos = fiscalizacaoRepository.listaTodosPaginada(0, 100);
	}

	@PostConstruct
	public void init() {
		this.fiscalizacaos = fiscalizacaoRepository.listaTodosPaginada(0, 100);
		System.out.println("@PostConstruct FiscalizacaoBean.init();");
	}
}
