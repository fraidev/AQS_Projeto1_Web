package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class HomeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		System.out.println("HomeBean ");
	}
	
	public String openUf() {
		return "/view/uf-form.xhtml";
	}
	
	public String openCidade() {
		return "/view/cidade-form.xhtml";
	}
	
	public String openBairro() {
		return "/view/bairro-form.xhtml";
	}
	
	public String openEmpresa() {
		return "/view/empresa-form.xhtml";
	}
	
	public String openFiscalizacao() {
		return "/view/fiscalizacao-form.xhtml";
	}
	
}
