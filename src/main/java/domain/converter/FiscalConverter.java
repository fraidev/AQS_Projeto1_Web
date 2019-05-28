package domain.converter;

import domain.models.Fiscal;
import domain.repositories.FiscalRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Fiscal.class)
public class FiscalConverter implements Converter {

    @Inject
    private FiscalRepository fiscalRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.valueOf(value);
        Fiscal fiscal = new Fiscal();
        if(fiscalRepository!= null){
            fiscal = fiscalRepository.buscaPorId(id);
        }else{
            fiscal.setId(id);
        }
        return fiscal;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        Fiscal fiscal = (Fiscal) value;

        return fiscal.getId().toString();
    }
}
