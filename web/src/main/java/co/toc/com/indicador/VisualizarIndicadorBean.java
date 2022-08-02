package co.toc.com.indicador;

import co.toc.com.indicador.entidades.Indicador;
import co.toc.com.indicador.entidades.RegistroIndicador;
import co.toc.com.indicador.servicios.IndicadorServicio;
import co.toc.com.indicador.servicios.RegistroIndicadorServicio;
import co.toc.com.indicador.utilidades.Utilidades;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class VisualizarIndicadorBean {


    @Autowired
    private RegistroIndicadorServicio registroIndicadorServicio;
    @Autowired
    private IndicadorServicio indicadorServicio;

    @Value("#{param['proceso']}")
    private String procesoBusqueda;
    @Getter
    @Setter
    private List<RegistroIndicador> listaRegistroIndicadores;

    @Getter
    @Setter
    private List<Indicador> listaIndicadoresProceso;

    @PostConstruct
    public void init() {

      //  listaRegistroIndicadores = registroIndicadorServicio.obtenerRegistroIndicador();
        listaIndicadoresProceso = indicadorServicio.obtenerIndicadorProceso(Integer.parseInt(procesoBusqueda));
    }

    /**
     * Obtiene los meses del indicador
     *
     * @return
     */
    public String obtenerMesesIndicador(RegistroIndicador registroIndicador) {
        String mes;
        Utilidades utilidades = new Utilidades();
        mes = utilidades.obtenerMes(registroIndicador);
        return mes;
    }

    /**
     *
     * @param idIndicador
     * @return
     */
    public List<RegistroIndicador> obtenerRegistroIndicador(int idIndicador){
        return registroIndicadorServicio.obtenerRegistroIndicador(idIndicador);
    }
}
