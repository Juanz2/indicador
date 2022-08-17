package co.toc.com.indicador.servicios;

import co.toc.com.indicador.entidades.RegistroIndicador;
import co.toc.com.indicador.repositorios.RegistroIndicadorRepo;
import co.toc.com.indicador.utilidades.Utilidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Utilities;
import java.util.List;

@Service
public class RegistroIndicadorServicioImp implements RegistroIndicadorServicio {


    @Autowired
    private RegistroIndicadorRepo registroIndicadorRepo;

    @Override
    public RegistroIndicador registrarIndicador(RegistroIndicador registroIndicador, boolean editar) throws Exception {

        if (editar) {
            return registroIndicadorRepo.save(registroIndicador);
        } else {
            List<RegistroIndicador> registroIndicadorValidar = registroIndicadorRepo.obtenerIndicadorValidar(registroIndicador.getMes(), registroIndicador.getIndicador().getIdIndicador(), registroIndicador.getAnio());
            Utilidades utilidades = new Utilidades();
            if (registroIndicadorValidar.size() > 0) {
                throw new Exception("El indicador " + registroIndicador.getIndicador().getNombre() + " ya se encuentra registrado " +
                        "para el mes " + utilidades.obtenerMes(registroIndicador) + " del año " + registroIndicador.getAnio());
            } else {
                return registroIndicadorRepo.save(registroIndicador);
            }
        }
    }

    @Override
    public RegistroIndicador obtenerRegistroIndicador(Integer id) {
        return registroIndicadorRepo.findById(id).orElse(null);
    }

    @Override
    public void inactivarRegistroIndicador(RegistroIndicador registroIndicador) {
        registroIndicador.setEstado("I");
        registroIndicadorRepo.save(registroIndicador);
    }

    @Override
    public List<RegistroIndicador> obtenerRegistroIndicadores() {
        return registroIndicadorRepo.obtenerListaRegistroIndicador();
    }

    @Override
    public List<RegistroIndicador> obtenerRegistroIndicadorProceso(int idProceso) {
        return registroIndicadorRepo.obtenerRegistroIndicadorProceso(idProceso);
    }

    @Override
    public List<RegistroIndicador> obtenerRegistroIndicador(int idIndicador) {
        return registroIndicadorRepo.obtenerRegistroIndicador(idIndicador);
    }
}
