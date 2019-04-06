package bean;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UfBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public String voltar() {
		return "/view/home.xhtml";
	}
}
