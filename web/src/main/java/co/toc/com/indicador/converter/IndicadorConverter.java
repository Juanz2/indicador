package co.toc.com.indicador.converter;

import co.toc.com.indicador.entidades.Indicador;
import co.toc.com.indicador.servicios.IndicadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class IndicadorConverter implements Converter<Indicador>, Serializable {


    @Autowired
    private IndicadorServicio indicadorServicio;

    @Override
    public Indicador getAsObject(FacesContext context, UIComponent component, String value) {

        Indicador indicador = indicadorServicio.obtenerIndicador(Integer.parseInt(value));
        return indicador;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Indicador value) {

        if(value != null){
            return value.getIdIndicador().toString();
        }
        return "";
    }
}
