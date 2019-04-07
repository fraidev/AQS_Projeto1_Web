package main.bean;

import main.dao.BairroDao;
import main.domain.Bairro;
import main.domain.Status;
import main.tx.Transacional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class BairroBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private BairroDao bairroDao;
	private Bairro bairro;
	private Bairro selected;
	private List<Bairro> bairros = new ArrayList<>();
	private Status status = Status.pesquisando;

	public void solicitaIncluir() {
		this.bairro = new Bairro();
		status = Status.incluindo;
	}

	public void solicitaAlterar() {
		this.bairro = selected;
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
	public boolean isMostraPesquisa() {
		this.bairros = bairroDao.listaTodosPaginada(0, 10);
		return (status == Status.pesquisando);
	}
	public boolean isDesabilitaAlteracao() {
		return (selected == null);
	}
	public boolean isDesabilitaExclusao() {
		return (selected == null);
	}

	public Bairro getSelected() {
		return selected;
	}
	public void setSelected(Bairro selected) {
		if (selected!= null) {
			System.out.println(selected.getId());
		}
		this.selected = selected;
	}
	public List<Bairro> getBairros() {
		return bairros;
	}

	public Bairro getBairro() {
		return this.bairro;
	}

	public List<Bairro> getTodos(){
		return this.bairroDao.listaTodos();
	}

	@Transacional
	public void confirmaInclusao(){
		this.bairroDao.adiciona(bairro);
		status = Status.pesquisando;
	}

	@Transacional
	public void confirmaAlteracao() {
		this.bairroDao.atualiza(bairro);
		this.selected = null;
		status = Status.pesquisando;
	}

	@Transacional
	public void solicitaExcluir() {
		this.bairroDao.remove(selected);
		this.selected = null;
	}

	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct BairroBean.init();");
	}
}
