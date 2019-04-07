package main.bean;

import main.dao.EmpresaDao;
import main.domain.Empresa;
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
public class EmpresaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaDao empresaDao;
	private Empresa empresa;
	private Empresa selected;
	private List<Empresa> empresas = new ArrayList<>();
	private Status status = Status.pesquisando;

	public void solicitaIncluir() {
		this.empresa = new Empresa();
		status = Status.incluindo;
	}

	public void solicitaAlterar() {
		this.empresa = selected;
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
		this.empresas = empresaDao.listaTodosPaginada(0, 10);
		return (status == Status.pesquisando);
	}
	public boolean isDesabilitaAlteracao() {
		return (selected == null);
	}
	public boolean isDesabilitaExclusao() {
		return (selected == null);
	}

	public Empresa getSelected() {
		return selected;
	}
	public void setSelected(Empresa selected) {
		if (selected!= null) {
			System.out.println(selected.getId());
		}
		this.selected = selected;
	}
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public List<Empresa> getTodos(){
		return this.empresaDao.listaTodos();
	}

	@Transacional
	public void confirmaInclusao(){
		this.empresaDao.adiciona(empresa);
		status = Status.pesquisando;
	}

	@Transacional
	public void confirmaAlteracao() {
		this.empresaDao.atualiza(empresa);
		this.selected = null;
		status = Status.pesquisando;
	}

	@Transacional
	public void solicitaExcluir() {
		this.empresaDao.remove(selected);
		this.selected = null;
	}

	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct EmpresaBean.init();");
	}
}
