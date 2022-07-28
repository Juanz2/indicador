package co.toc.com.indicador.servicios;

import co.toc.com.indicador.entidades.Proceso;

import java.util.List;

public interface ProcesoServicio {

    Proceso registrarProceso(Proceso proceso) throws Exception;

    Proceso obtenerProceso(Integer id) throws Exception;

    void inactivarProceso(Proceso proceso) throws Exception;

    List<Proceso> obtenerProcesos();

    List<Proceso> obtenerProcesosUsuario(int idUsuario);
}
