package bean;

import bean.models.Status;
import domain.models.*;
import domain.repositories.BairroRepository;
import domain.repositories.CidadeRepository;
import domain.repositories.EmpresaRepository;
import domain.repositories.UfRepository;
import domain.validators.CpfCnpjUtils;
import infrastructure.tx.Transacional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class EmpresaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaRepository empresaRepository;
	@Inject
	private CidadeRepository cidadeRepository;
	@Inject
	private BairroRepository bairroRepository;
	@Inject
	private UfRepository ufRepository;
	private Cidade cidade;
	private Bairro bairro;
	private Uf uf;
	private Empresa empresa;
	private Empresa selected;
	private List<Bairro> bairros = new ArrayList<>();
	private List<Cidade> cidades = new ArrayList<>();
	private List<Empresa> empresas = new ArrayList<>();
	private Status status = Status.pesquisando;
	private String nome;
	private String warning;

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
		this.onUfChange();
		this.onCidadeChange();
		status = Status.alterando;
	}

	public void pesquisar() {
		this.empresas = empresaRepository.pesquisar(this.nome);
		this.selected = null;
	}

	public void cancelar() {
		status = Status.pesquisando;
		this.empresas = empresaRepository.listaTodosPaginada(0, 100);
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

	public void onUfChange() {
		if(empresa.getUf() != null)
			cidades = ufRepository.listaTodos().stream().filter(x  -> x.getId() == empresa.getUf().getId()).findFirst().get().getCidades();
		else
			cidades = new ArrayList<>();
	}

	public void onCidadeChange() {
		if(empresa.getCidade() != null)
			bairros = cidadeRepository.listaTodos().stream().filter(x  -> x.getId() == empresa.getCidade().getId()).findFirst().get().getBairros();
		else
			bairros = new ArrayList<>();
	}

	public List<Empresa> getTodos(){
		return this.empresaRepository.listaTodos();
	}

	public List<Uf> getTodosUf(){
		return this.ufRepository.listaTodos();
	}

	@Transacional
	public void confirmaInclusao(){
		if(!CpfCnpjUtils.isValidCNPJ(empresa.getCnpj())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "CNPJ Invalidado."));
			return;
		}

		this.empresaRepository.adiciona(empresa);
		status = Status.pesquisando;
		this.empresas = empresaRepository.listaTodosPaginada(0, 100);
	}

	@Transacional
	public void confirmaAlteracao() {
		if(!CpfCnpjUtils.isValidCNPJ(empresa.getCnpj())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "CNPJ Invalidado."));
			return;
		}

		this.empresaRepository.atualiza(empresa);
		this.selected = null;
		status = Status.pesquisando;
		this.empresas = empresaRepository.listaTodosPaginada(0, 100);
	}

	@Transacional
	public void solicitaExcluir() {
		this.empresaRepository.remove(selected);
		this.selected = null;
		this.empresas = empresaRepository.listaTodosPaginada(0, 100);
	}

	@PostConstruct
	public void init() {
		this.empresas = empresaRepository.listaTodosPaginada(0, 100);
		System.out.println("@PostConstruct EmpresaBean.init();");
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}
}
