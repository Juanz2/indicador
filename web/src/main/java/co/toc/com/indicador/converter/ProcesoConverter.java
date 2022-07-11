package co.toc.com.indicador.converter;

import co.toc.com.indicador.entidades.Proceso;
import co.toc.com.indicador.servicios.ProcesoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class ProcesoConverter implements Converter<Proceso>, Serializable {

    @Autowired
    private ProcesoServicio procesoServicio;

    @Override
    public Proceso getAsObject(FacesContext context, UIComponent component, String value) {

        try {
            Proceso proceso = procesoServicio.obtenerProceso(Integer.parseInt(value));
            return proceso;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Proceso value) {
        if (value != null) {
            return value.getIdProceso().toString();
        }
        return "";
    }
}
