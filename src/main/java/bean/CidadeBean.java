package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CidadeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		System.out.println("CidadeBeana;");
	}
	
	public String voltar() {
		return "/view/home.xhtml";
	}
}
