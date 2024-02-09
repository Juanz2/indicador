package co.toc.com.indicador.bean;

import co.toc.com.indicador.entidades.Proceso;
import co.toc.com.indicador.entidades.Usuario;
import co.toc.com.indicador.servicios.ProcesoServicio;
import co.toc.com.indicador.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Component
public class UsuarioBean implements Serializable {


    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProcesoServicio procesoServicio;

    @Getter
    @Setter
    private List<Proceso> listaProcesos;
    @Getter
    @Setter
    private Usuario usuarioCrear;

    @Getter
    @Setter
    private Usuario usuarioSeleccionado;

    @Getter
    @Setter
    private List<Proceso> listaProcesosSeleccionados;

    @Getter
    @Setter
    private List<Usuario> listaUsuarios;

    @Getter
    @Setter
    private int tipoOperacion = 1;

    @PostConstruct
    public void init() {
        usuarioCrear = new Usuario();
        listaProcesosSeleccionados = new ArrayList<>();
        listaProcesos = procesoServicio.obtenerProcesos();
        listaUsuarios = usuarioServicio.obtenerUsuarios();
    }

    /**
     * Método para realizar el registro o actualización del usuario
     */
    public String registrarUsuario() {

        try {


            usuarioCrear.setEstado("A");
            usuarioCrear.setProcesos(listaProcesosSeleccionados);
            usuarioCrear.setPassword("new@Pass#%%12");
            usuarioServicio.registrarUsuario(usuarioCrear, tipoOperacion);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta\n", "Procesado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "/administrarUsuarios?faces-redirect=true";

        } catch (Exception e) {
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta\n", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
        return null;
    }

    /**
     * Selecciona el usuario de la tabla
     *
     * @param u usuario
     */
    public void seleccionarUsuario(Usuario u, int tipoOperacion) {
        this.usuarioCrear = u;
        this.listaProcesosSeleccionados = procesoServicio.obtenerProcesosUsuario(u.getIdUsuario());
        this.tipoOperacion = tipoOperacion;
    }

    public String inactivarUsuario() {

        usuarioCrear.setEstado("I");
        try {
            usuarioServicio.registrarUsuario(usuarioCrear, 2);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta\n", "Procesado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "/administrarUsuarios?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación\n", "Error al actualizar el usuario");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
        return null;
    }
}
