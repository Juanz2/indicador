package co.toc.com.indicador.converter;

import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class AnioConverter implements Converter<String>, Serializable {


    @Override
    public String getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, String value) {
        return value;
    }

}
