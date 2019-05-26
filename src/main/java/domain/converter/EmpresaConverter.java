package domain.converter;

import domain.models.Empresa;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Empresa.class)
public class EmpresaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.valueOf(value);
        Empresa empresa = new Empresa();
        empresa.setId(id);
        return empresa;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        Empresa empresa = (Empresa) value;

        return empresa.getId().toString();
    }
}
