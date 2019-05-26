package bean;

import domain.repository.FiscalizacaoDao;
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
	private FiscalizacaoDao fiscalizacaoDao;
	private Fiscalizacao fiscalizacao;
	private Fiscalizacao selected;
	private List<Fiscalizacao> fiscalizacaos = new ArrayList<>();
	private Status status = Status.pesquisando;
	private int page = 1;
	
	public void solicitaIncluir() {
		this.fiscalizacao = new Fiscalizacao();
		status = Status.incluindo;
	}

	public void solicitaAlterar() {
		this.fiscalizacao = selected;
		status = Status.alterando;
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
	public void avancaPage() {
		this.page ++;
		this.fiscalizacaos = fiscalizacaoDao.listaTodosPaginada(this.page, 10);
	}
	public void voltaPage() {
		if(this.page > 1) {
			this.page --;
		this.fiscalizacaos = fiscalizacaoDao.listaTodosPaginada(this.page, 10);
		}
	}
	public boolean isMostraPesquisa() {
		this.fiscalizacaos = fiscalizacaoDao.listaTodosPaginada(0, 10);
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
		return this.fiscalizacaoDao.listaTodos();
	}

	@Transacional
	public void confirmaInclusao(){
		this.fiscalizacaoDao.adiciona(fiscalizacao);
		status = Status.pesquisando;
	}

	@Transacional
	public void confirmaAlteracao() {
		this.fiscalizacaoDao.atualiza(fiscalizacao);
		this.selected = null;
		status = Status.pesquisando;
	}

	@Transacional
	public void solicitaExcluir() {
		this.fiscalizacaoDao.remove(selected);
		this.selected = null;
	}

	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct FiscalizacaoBean.init();");
	}
}
