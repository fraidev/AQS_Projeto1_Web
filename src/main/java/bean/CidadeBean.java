package bean;

import domain.repository.CidadeDao;
import domain.models.Cidade;
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
public class CidadeBean implements Serializable {
	private static final long serialVersionUID = 1L;

    @Inject
    private CidadeDao cidadeDao;
    private Cidade cidade;
    private Cidade selected;
    private List<Cidade> cidades = new ArrayList<>();
	private Status status = Status.pesquisando;

	public void solicitaIncluir() {
		this.cidade = new Cidade();
		status = Status.incluindo;
	}
	
	public void solicitaAlterar() {
		this.cidade = selected;
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
		this.cidades = cidadeDao.listaTodosPaginada(0, 10);
		return (status == Status.pesquisando);
	}
	public boolean isDesabilitaAlteracao() {
		return (selected == null);
	}
	public boolean isDesabilitaExclusao() {
		return (selected == null);
	}
	
	public Cidade getSelected() {
		return selected;
	}
	public void setSelected(Cidade selected) {
		if (selected!= null) {
			System.out.println(selected.getId());
		}
		this.selected = selected;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}

	public Cidade getCidade() {
		return this.cidade;
	}

	public List<Cidade> getTodos(){
		return this.cidadeDao.listaTodos();
	}

	@Transacional
	public void confirmaInclusao(){
		this.cidadeDao.adiciona(cidade);
		status = Status.pesquisando;
	}
	
	@Transacional
	public void confirmaAlteracao() {
		this.cidadeDao.atualiza(cidade);
		this.selected = null;
		status = Status.pesquisando;
	}
	
	@Transacional
	public void solicitaExcluir() {
		this.cidadeDao.remove(selected);
		this.selected = null;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct CidadeBean.init();");
	}
}
