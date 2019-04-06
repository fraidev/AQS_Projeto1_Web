package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class UfBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		System.out.println("UfBean;");
	}
	
	public String voltar() {
		System.out.println("UfBean;");
		return "home";
	}
}
