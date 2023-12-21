package co.toc.com.indicador;


import co.toc.com.indicador.entidades.Indicador;
import co.toc.com.indicador.entidades.Proceso;
import co.toc.com.indicador.servicios.IndicadorServicio;
import co.toc.com.indicador.servicios.ProcesoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Component
public class IndicadorBean implements Serializable {

    @Autowired
    private IndicadorServicio indicadorServicio;

    @Autowired
    private ProcesoServicio procesoServicio;

    @Getter
    @Setter
    private Indicador indicador;

    @Getter
    @Setter
    private List<Proceso> listaProcesos;

    @Getter
    @Setter
    private List<Indicador> listaIndicadores;




    @PostConstruct
    public void init() {

        indicador = new Indicador();
        listaProcesos = procesoServicio.obtenerProcesos();
        listaIndicadores = indicadorServicio.obtenerListaIndicadores();
    }

    public String registrarIndicador() {

        indicador.setEstado("A");
        indicadorServicio.registrarIndicador(indicador);

        return "/administrarIndicadores?faces-redirect=true";
    }

    public String inactivarIndicador() {
        indicador.setEstado("I");
        indicadorServicio.registrarIndicador(indicador);
        return "/administrarIndicadores?faces-redirect=true";
    }

    /**
     * Obtiene el indicador seleccionado por el usuario
     *
     * @param indicador
     */
    public void seleccionarIndicador(Indicador indicador) {
        this.indicador = indicador;
    }
}
