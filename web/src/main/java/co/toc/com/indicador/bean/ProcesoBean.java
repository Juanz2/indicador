package co.toc.com.indicador.bean;

import co.toc.com.indicador.entidades.Proceso;
import co.toc.com.indicador.servicios.ProcesoServicio;
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
import java.util.List;

@ViewScoped
@Component
public class ProcesoBean implements Serializable {

    @Autowired
    private ProcesoServicio procesoServicio;

    @Value("${upload.url}")
    private String urlImagenes;

    @Getter
    @Setter
    private ArrayList<String> imagenes;

    @Getter
    @Setter
    private List<Proceso> listaProcesos;

    @Getter
    @Setter
    Proceso proceso;


    @PostConstruct
    public void init() {
        imagenes = new ArrayList<>();
        proceso = new Proceso();
        listaProcesos = procesoServicio.obtenerProcesos();
    }

    /*
     * @param event evento
     */
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

    /**
     * Permite registrar el proceso.
     *
     * @return Redirección a la pagina actual
     */
    public String registrarProceso() {


        try {
            proceso.setEstado("A");
            proceso.setFoto(imagenes.get(0));
            procesoServicio.registrarProceso(proceso);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta\n", "Procesado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "/administrarProcesos?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta\n", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
        return null;
    }

    /**
     * Metodo que permite cambiar el estado del proceso
     *
     * @return Redirección a la página actual
     */
    public String inactivarProceso() {

        try {
            proceso.setEstado("I");
            procesoServicio.registrarProceso(proceso);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta\n", "Procesado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "/administrarProcesos?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta\n", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
        return null;
    }

    /**
     * Obtiene el proceso seleccionado por el usuario
     *
     * @param proceso
     */
    public void seleccionarProceso(Proceso proceso) {
        this.proceso = proceso;
    }
}
