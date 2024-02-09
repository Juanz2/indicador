package co.toc.com.indicador.bean;

import co.toc.com.indicador.entidades.Usuario;
import co.toc.com.indicador.servicios.UsuarioServicio;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

@ViewScoped
@Component
@Data
public class PerfilBean implements Serializable{


    private Usuario usuario;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{param['usuario']}")
    private String perfil;
    private boolean cambiado = false;

    private String password1;

    private String password2;

    private ArrayList<String> imagenes;

    @Value("${upload.url}")
    private String urlImagenes;



    @PostConstruct
    public void init() {
        try {
            usuario = usuarioServicio.obtenerUsuario(Integer.parseInt(perfil));
            imagenes = new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarUsuario(){

        try {
            if(!imagenes.isEmpty())
                usuario.setFoto(imagenes.get(0));
            usuarioServicio.registrarUsuario(usuario,2);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta\n", "Cambio realizado con éxito");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        } catch (Exception e) {
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta\n", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
    }

    /**
     * Permite modificar la contraseña del usuario
     */
    public void cambiarPassword() {

        if (!password1.isEmpty() || !password2.isEmpty()) {
            if (!password1.equals(password2)) {
                FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta\n", "Las contraseñas no coinciden");
                FacesContext.getCurrentInstance().addMessage(null, msj);
            } else {
                try {
                    Usuario usuario = usuarioServicio.obtenerUsuario(Integer.parseInt(perfil));
                    usuario.setPassword(password1);
                    usuarioServicio.registrarUsuario(usuario, 2);
                    cambiado = true;
                    FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta\n", "Cambio realizado con éxito");
                    FacesContext.getCurrentInstance().addMessage(null, msj);
                } catch (Exception e) {
                    FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta\n", e.getMessage());
                    FacesContext.getCurrentInstance().addMessage(null, msj);
                }
            }
        }
    }
    public void subirImagenes(FileUploadEvent event) {
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if (nombreImagen != null) {
            imagenes.add(nombreImagen);
        }
    }
    /**
     * @param imagen imagen
     * @return nombre de archivo
     */
    public String subirImagen(UploadedFile imagen) {
        File archivo = new File(urlImagenes + "/" + imagen.getFileName());
        try {
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return imagen.getFileName();
    }


}
