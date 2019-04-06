package main.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import main.dao.UfDao;
import main.domain.Uf;
import main.tx.Transacional;



@Named
@ViewScoped
public class UfBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private enum Status {pesquisando, incluindo, alterando};
    @Inject
    private UfDao ufDao;
    private Uf uf;
    private Uf selectedUf;
	private String nome;
    private List<Uf> ufs = new ArrayList<>();
	private Status status = Status.pesquisando; 

	
	public String openUf() {
		return "/view/uf-form.xhtml";
	}
	
	public void solicitaIncluir() {
		this.uf = new Uf();
		status = Status.incluindo;
	}
	
	public void solicitaAlterar() {
		this.uf = selectedUf;
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
		return (selectedUf == null);
	}
	public boolean isDesabilitaExclusao() {
		return (selectedUf == null);
	}
	
	public Uf getSelectedUf() {
		return selectedUf;
	}
	public void setSelectedUf(Uf selectedUsuario) {
		if (selectedUf != null) {
			System.out.println(selectedUf.getId());
		}
		this.selectedUf = selectedUsuario;
	}
	public List<Uf> getUfs() {
		return ufs;
	}
	public String getNome() {
		return nome;
	}
	public void setLogin(String nome) {
		this.nome = nome.trim().toLowerCase();
	}
	public Uf getUf() {
		return this.uf;
	}

	@Transacional
	public void confirmaInclusao() {
		this.ufDao.adiciona(uf);
	}
	
	@Transacional
	public void confirmaAlteracao() {
		this.ufDao.atualiza(uf);
	}
	
	@Transacional
	public void solicitaExcluir() {
		this.ufDao.remove(uf);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct GestorLoginBean.init();");
	}
	
	public void pesquisar() {
//		uf = ufDao.pesquisa();
//		selectedU= null;
	}
}
