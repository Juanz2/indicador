package co.toc.com.indicador;

import co.toc.com.indicador.entidades.Proceso;
import co.toc.com.indicador.servicios.ProcesoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class DashBoardBean {

    @Autowired
    private ProcesoServicio procesoServicio;

    @Getter
    @Setter
    private List<Proceso> listaProcesos;

    @PostConstruct
    public void init() {

        listaProcesos = procesoServicio.obtenerProcesos();
    }

    /**
     *
     * @param proceso
     * @return
     */
    public String visualizarIndicadorProceso (Proceso proceso){

        return "indicador?faces-redirect=true&amp;proceso=" + proceso.getIdProceso();
    }
}
