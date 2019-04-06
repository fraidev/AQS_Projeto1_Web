package main.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import main.dao.UfDao;
import main.domain.Status;
import main.domain.Uf;
import main.tx.Transacional;



@Named
@ViewScoped
public class UfBean implements Serializable {
	private static final long serialVersionUID = 1L;

    @Inject
    private UfDao ufDao;
    private Uf uf;
    private Uf selected;
    private List<Uf> ufs = new ArrayList<>();
	private Status status = Status.pesquisando;
	
	public void solicitaIncluir() {
		this.uf = new Uf();
		status = Status.incluindo;
	}
	
	public void solicitaAlterar() {
		this.uf = selected;
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
		this.ufs = ufDao.listaTodosPaginada(0, 10);
		return (status == Status.pesquisando);
	}
	public boolean isDesabilitaAlteracao() {
		return (selected == null);
	}
	public boolean isDesabilitaExclusao() {
		return (selected == null);
	}
	
	public Uf getSelected() {
		return selected;
	}
	public void setSelected(Uf selected) {
		if (selected != null) {
			System.out.println(selected.getId());
		}
		this.selected = selected;
	}
	public List<Uf> getUfs() {
		return ufs;
	}
	public Uf getUf() {
		return this.uf;
	}

	@Transacional
	public void confirmaInclusao() {
		this.ufDao.adiciona(uf);
		status = Status.pesquisando;
	}
	
	@Transacional
	public void confirmaAlteracao() {
		this.ufDao.atualiza(uf);
		this.selected = null;
		status = Status.pesquisando;
	}
	
	@Transacional
	public void solicitaExcluir() {
		this.ufDao.remove(selected);
		this.selected = null;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct UfBean.init();");
	}
}
