package co.toc.com.indicador;


import co.toc.com.indicador.entidades.Indicador;
import co.toc.com.indicador.entidades.RegistroIndicador;
import co.toc.com.indicador.entidades.Usuario;
import co.toc.com.indicador.servicios.IndicadorServicio;
import co.toc.com.indicador.servicios.RegistroIndicadorServicio;
import co.toc.com.indicador.utilidades.Utilidades;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@ViewScoped
public class RegistroBean implements Serializable {


    @Autowired
    private RegistroIndicadorServicio registroIndicadorServicio;

    @Autowired
    private IndicadorServicio indicadorServicio;

    @Getter
    @Setter
    private List<Indicador> listaIndicadores;

    @Getter
    @Setter
    private List<RegistroIndicador> listaRegistroIndicadores;

    @Getter
    @Setter
    private RegistroIndicador registroIndicador;

    @Value("#{param['proceso']}")
    private String procesoBusqueda;

    @Value(value = "#{seguridadBean.usuario}")
    private Usuario usuarioSesion;


    @PostConstruct
    public void init() {
        registroIndicador = new RegistroIndicador();
        listaIndicadores = indicadorServicio.obtenerIndicadorProceso(Integer.parseInt(procesoBusqueda));
        listaRegistroIndicadores = registroIndicadorServicio.obtenerRegistroIndicadorProceso(Integer.parseInt(procesoBusqueda));
    }

    /**
     * Realiza la creación del indicador
     */
    public String registrarIndicador() {

        registroIndicador.setEstado("A");
        registroIndicador.setFechaCreacion(LocalDateTime.now());
        registroIndicador.setUsuarioCreacion(usuarioSesion);

        registroIndicadorServicio.registarIndicador(registroIndicador);

        return "/registrarIndicador?proceso="+procesoBusqueda+"faces-redirect=true";

    }

    /**
     * Obtiene el registro de indicador seleccionado por el usuario
     *
     * @param registroIndicador
     */
    public void seleccionarRegistroIndicador(RegistroIndicador registroIndicador) {
        this.registroIndicador = registroIndicador;
    }

    public String inactivarRegistroIndicador() {
        registroIndicadorServicio.inactivarRegistroIndicador(registroIndicador);
        return "/registrarIndicador?proceso="+procesoBusqueda+"faces-redirect=true";

    }

    /**
     * Obtiene la fecha en formato yyyy-MM-dd
     *
     * @param fecha
     * @return
     */
    public String obtenerFecha(LocalDateTime fecha) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return fecha.format(formato);
    }

    /**
     * Obtiene el mes del Indicador
     *
     * @param registroIndicador
     * @return
     */
    public String obtenerMes(RegistroIndicador registroIndicador) {
        Utilidades utilidades = new Utilidades();
        return utilidades.obtenerMes(registroIndicador);
    }
}
