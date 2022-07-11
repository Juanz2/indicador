package co.toc.com.indicador;

import co.toc.com.indicador.entidades.Proceso;
import co.toc.com.indicador.entidades.Usuario;
import co.toc.com.indicador.servicios.ProcesoServicio;
import co.toc.com.indicador.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
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
    private Usuario usuario;

    @Getter
    @Setter
    private List<Proceso> listaProcesosSeleccionados;

    @Getter
    @Setter
    private List<Usuario> listaUsuarios;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        usuario.setEstado("A");
        listaProcesosSeleccionados = new ArrayList<>();
        listaProcesos = procesoServicio.obtenerProcesos();
        listaUsuarios = usuarioServicio.obtenerUsuarios();
    }

    /**
     * Método para realizar el registro o actualización del usuario
     */
    public String registrarUsuario() {

        try {
            if (listaProcesosSeleccionados.isEmpty()) {
                FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación\n", "El usuario debe tener al menos un proceso relacionado");
                FacesContext.getCurrentInstance().addMessage(null, msj);
            } else {
                usuario.setProcesos(listaProcesosSeleccionados);
                usuario.setPassword("new@Pass#%%12");
                usuarioServicio.registrarUsuario(usuario, 1);
                FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta\n", "Procesado correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msj);
                usuario = new Usuario();
                listaUsuarios = usuarioServicio.obtenerUsuarios();
                return "/administrarUsuarios?faces-redirect=true";
            }

        } catch (Exception e) {
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta\n", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
        return null;
    }

    /**
     * Selecciona el usuario de la tabla
     * @param u usuario
     */
    public void seleccionarUsuario(Usuario u) {
        this.usuario = u;
    }
}
