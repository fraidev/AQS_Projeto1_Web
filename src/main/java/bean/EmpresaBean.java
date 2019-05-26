package bean;

import domain.repositories.EmpresaRepository;
import domain.models.Empresa;
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
public class EmpresaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaRepository empresaRepository;
	private Empresa empresa;
	private Empresa selected;
	private List<Empresa> empresas = new ArrayList<>();
	private Status status = Status.pesquisando;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public void solicitaIncluir() {
		this.empresa = new Empresa();
		status = Status.incluindo;
	}

	public void solicitaAlterar() {
		this.empresa = selected;
		status = Status.alterando;
	}

	public void pesquisar() {
		this.empresas = empresaRepository.pesquisar(this.nome);
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
		return this.empresaRepository.listaTodos();
	}

	@Transacional
	public void confirmaInclusao(){
		this.empresaRepository.adiciona(empresa);
		status = Status.pesquisando;
	}

	@Transacional
	public void confirmaAlteracao() {
		this.empresaRepository.atualiza(empresa);
		this.selected = null;
		status = Status.pesquisando;
	}

	@Transacional
	public void solicitaExcluir() {
		this.empresaRepository.remove(selected);
		this.selected = null;
	}

	@PostConstruct
	public void init() {
		this.empresas = empresaRepository.listaTodosPaginada(0, 100);
		System.out.println("@PostConstruct EmpresaBean.init();");
	}
}
