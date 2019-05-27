package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import domain.models.Bairro;
import domain.models.Cidade;
import domain.repositories.UfRepository;
import domain.models.Status;
import domain.models.Uf;
import infrastructure.tx.Transacional;



@Named
@ViewScoped
public class UfBean implements Serializable {
	private static final long serialVersionUID = 1L;

    @Inject
    private UfRepository ufRepository;
    private Uf uf;
    private Uf selected;
    private List<Uf> ufs = new ArrayList<>();
	private Status status = Status.pesquisando;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLogin(String nome) {
		this.nome = nome.trim().toLowerCase();
	}
	
	public void solicitaIncluir() {
		this.uf = new Uf();
		status = Status.incluindo;
	}
	
	public void solicitaAlterar() {
		this.uf = selected;
		status = Status.alterando;
	}

	public void pesquisar() {
		this.setUfs(ufRepository.pesquisar(this.nome));
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
	public void setUfs(List<Uf> ufs) {
		this.ufs = ufs;
	}
	public Uf getUf() {
		return this.uf;
	}
	
	public List<Uf> getTodos(){
		return this.ufRepository.listaTodos();
	}

	@Transacional
	public void confirmaInclusao() {
		this.ufRepository.adiciona(uf);
		status = Status.pesquisando;
		this.ufs = getTodos();
	}
	
	@Transacional
	public void confirmaAlteracao() {
		this.ufRepository.atualiza(uf);
		this.selected = null;
		status = Status.pesquisando;
		this.ufs = getTodos();
	}
	
	@Transacional
	public void solicitaExcluir() {
		this.ufRepository.remove(selected);
		this.selected = null;
		this.ufs = getTodos();
	}
	
	@PostConstruct
	public void init() {
		this.ufs = getTodos();
		System.out.println("@PostConstruct UfBean.init();");
	}
}
