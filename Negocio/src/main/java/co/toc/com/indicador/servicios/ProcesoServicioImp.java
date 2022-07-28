package co.toc.com.indicador.servicios;

import co.toc.com.indicador.entidades.Proceso;
import co.toc.com.indicador.repositorios.ProcesoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesoServicioImp implements ProcesoServicio {

    @Autowired
    private ProcesoRepo procesoRepo;

    @Override
    public Proceso registrarProceso(Proceso proceso) throws Exception {
        return procesoRepo.save(proceso);
    }

    @Override
    public Proceso obtenerProceso(Integer id) throws Exception {
        return procesoRepo.findById(id).orElse(null);
    }

    @Override
    public void inactivarProceso(Proceso proceso) throws Exception {

        proceso.setEstado("I");
        procesoRepo.save(proceso);
    }

    @Override
    public List<Proceso> obtenerProcesos() {
        return procesoRepo.obtenerProcesos();
    }

    @Override
    public List<Proceso> obtenerProcesosUsuario(int idUsuario) {
        return procesoRepo.obtenerProcesosUsuario(idUsuario);
    }
}
