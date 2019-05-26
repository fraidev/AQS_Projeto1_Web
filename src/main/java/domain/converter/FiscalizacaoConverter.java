package domain.converter;

import domain.models.Fiscalizacao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Fiscalizacao.class)
public class FiscalizacaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.valueOf(value);
        Fiscalizacao fiscalizacao = new Fiscalizacao();
        fiscalizacao.setId(id);
        return fiscalizacao;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        Fiscalizacao fiscalizacao = (Fiscalizacao) value;

        return fiscalizacao.getId().toString();
    }
}
