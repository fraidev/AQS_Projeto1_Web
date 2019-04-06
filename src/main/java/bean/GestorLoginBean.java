//package bean;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
////import javax.enterprise.context.ApplicationScoped;
////import javax.faces.view.ViewScoped;
////import javax.enterprise.context.RequestScoped;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import javax.servlet.http.HttpSession;
//
//import dao.UsuarioDao;
//import modelo.negocio.Usuario;
//import tx.Transacional;
//
//@Named
//@ViewScoped
//public class GestorLoginBean implements Serializable {
//	
//	private static final long serialVersionUID = 1L;
//
//	private enum Status {pesquisando, incluindo, alterando};
//	@Inject
//	private UsuarioDao usuarioDao;
//	private Usuario usuario;
//	private String login;
//	private List<Usuario> usuarios = new ArrayList<>();
//	private Usuario selectedUsuario;
//	private Status status = Status.pesquisando; 
//	
//	
//	public void solicitaIncluir() {
//		usuario = new Usuario();
//		status = Status.incluindo;
//	}
//	public void cancelar() {
//		status = Status.pesquisando;
//	}
//	public boolean isMostraEdicao() {
//		return (status == Status.incluindo) || (status == Status.alterando);
//	}
//	public boolean isIncluindo() {
//		return (status == Status.incluindo);
//	}
//	public boolean isAlterando() {
//		return (status == Status.alterando);
//	}
//	public boolean isMostraPesquisa() {
//		return (status == Status.pesquisando);
//	}
//	public boolean isDesabilitaAlteracao() {
//		return (selectedUsuario == null);
//	}
//	public boolean isDesabilitaExclusao() {
//		return (selectedUsuario == null);
//	}
//	
//	public Usuario getSelectedUsuario() {
//		return selectedUsuario;
//	}
//	public void setSelectedUsuario(Usuario selectedUsuario) {
//		if (selectedUsuario != null) {
//			System.out.println(selectedUsuario.getId());
//		}
//		this.selectedUsuario = selectedUsuario;
//	}
//	public List<Usuario> getUsuarios() {
//		return usuarios;
//	}
//	public String getLogin() {
//		return login;
//	}
//	public void setLogin(String login) {
//		this.login = login.trim().toLowerCase();
//	}
//	public Usuario getUsuario() {
//		return usuario;
//	}
//	@PostConstruct
//	public void init() {
//		System.out.println("@PostConstruct GestorLoginBean.init();");
//	}
//	
//	public void pesquisar() {
//		usuarios = usuarioDao.pesquisa(login);
//		selectedUsuario = null;
//	}
//
//	
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
