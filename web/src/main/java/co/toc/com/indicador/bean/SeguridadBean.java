package co.toc.com.indicador;

import co.toc.com.indicador.entidades.Usuario;
import co.toc.com.indicador.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter
    @Setter
    private Usuario usuario;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private boolean autenticado;


    private final UsuarioServicio usuarioServicio;

    public SeguridadBean(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    /**
     * Inicia sesión, persiste los datos del usuario en toda la ejecución del programa
     * @return Redirección a la página principal
     */
    public String iniciarSesion() {
        try {
            usuario = usuarioServicio.obtenerUsuarioLogin(email, password);
            autenticado = true;
            return "/dashboard?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("", fm);
        }
        return null;
    }

    /**
     * Cierra la sesión del usuario
     * @return Redirección página login
     */
    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public String mostrarPerfil(){

        return "/perfil?faces-redirect=true&amp;usuario=" + usuario.getIdUsuario();
    }
}
