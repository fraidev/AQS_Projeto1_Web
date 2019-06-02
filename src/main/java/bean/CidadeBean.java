package bean;

import bean.models.Status;
import domain.repositories.CidadeRepository;
import domain.models.Cidade;
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
    private CidadeRepository cidadeRepository;
    private Cidade cidade;
    private Cidade selected;
    private List<Cidade> cidades = new ArrayList<>();
	private Status status = Status.pesquisando;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public void solicitaIncluir() {
		this.cidade = new Cidade();
		status = Status.incluindo;
	}
	
	public void solicitaAlterar() {
		this.cidade = selected;
		status = Status.alterando;
	}

	public void pesquisar() {
		this.cidades = cidadeRepository.pesquisar(this.nome);
		this.selected = null;
	}
	
	public void cancelar() {
		status = Status.pesquisando;
		this.cidades = getTodos();
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
		return status == Status.pesquisando;
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
		return this.cidadeRepository.listaTodos();
	}

	@Transacional
	public void confirmaInclusao(){
		this.cidadeRepository.adiciona(cidade);
		status = Status.pesquisando;
		this.cidades = cidadeRepository.listaTodos();
	}
	
	@Transacional
	public void confirmaAlteracao() {
		this.cidadeRepository.atualiza(cidade);
		this.selected = null;
		status = Status.pesquisando;
		this.cidades = cidadeRepository.listaTodos();
	}
	
	@Transacional
	public void solicitaExcluir() {
		this.cidadeRepository.remove(selected);
		this.selected = null;
		this.cidades = cidadeRepository.listaTodos();
	}
	
	@PostConstruct
	public void init() {
		this.cidades = cidadeRepository.listaTodos();
		System.out.println("@PostConstruct CidadeBean.init();");
	}
}
