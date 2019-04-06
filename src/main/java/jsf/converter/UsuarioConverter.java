//package .jsf.converter;
//
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//
//import modelo.negocio.Usuario;
//
//@FacesConverter(forClass = Usuario.class)
//public class UsuarioConverter implements Converter {
//
//	@Override
//	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		if (value == null) {
//			return null;
//		}
//		Long id = Long.valueOf(value);
//		Usuario usuario = new Usuario();
//		usuario.setId(id);
//		return usuario;
//	}
//
//	@Override
//	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		if (value == null) {
//			return null;
//		}
//
//		Usuario cliente = (Usuario) value;
//
//		return cliente.getId().toString();
//	}
//
//}
