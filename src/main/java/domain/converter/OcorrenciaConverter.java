package domain.converter;

import domain.models.Ocorrencia;
import domain.repositories.OcorrenciaRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Ocorrencia.class)
public class OcorrenciaConverter implements Converter {

    @Inject
    private OcorrenciaRepository ocorrenciaRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.valueOf(value);
        Ocorrencia ocorrencia = new Ocorrencia();
        if(ocorrenciaRepository!= null){
            ocorrencia = ocorrenciaRepository.buscaPorId(id);
        }else{
            ocorrencia.setId(id);
        }
        return ocorrencia;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        Ocorrencia ocorrencia = (Ocorrencia) value;

        return ocorrencia.getId().toString();
    }
}
