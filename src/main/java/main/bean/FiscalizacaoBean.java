package main.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class FiscalizacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		System.out.println("FiscalizacaoBean;");
	}
	
	public String voltar() {
		return "/view/home.xhtml";
	}
}
