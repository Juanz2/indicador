package co.toc.com.indicador.servicios;

import co.toc.com.indicador.entidades.RegistroIndicador;
import co.toc.com.indicador.repositorios.RegistroIndicadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroIndicadorServicioImp implements RegistroIndicadorServicio {


    @Autowired
    private RegistroIndicadorRepo registroIndicadorRepo;

    @Override
    public RegistroIndicador registarIndicador(RegistroIndicador registroIndicador) {
        return registroIndicadorRepo.save(registroIndicador);
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
