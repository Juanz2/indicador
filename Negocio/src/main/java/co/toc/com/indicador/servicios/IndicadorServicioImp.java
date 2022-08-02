package co.toc.com.indicador.servicios;

import co.toc.com.indicador.entidades.Indicador;
import co.toc.com.indicador.repositorios.IndicadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicadorServicioImp implements IndicadorServicio {


    @Autowired
    private IndicadorRepo indicadorRepo;

    @Override
    public Indicador registrarIndicador(Indicador indicador) {
        return indicadorRepo.save(indicador);
    }

    @Override
    public Indicador obtenerIndicador(Integer id) {
        return indicadorRepo.getById(id);
    }

    @Override
    public void inactivarIndicador(Indicador indicador) {
        indicadorRepo.save(indicador);
    }

    @Override
    public List<Indicador> obtenerListaIndicadores() {
        return indicadorRepo.obtenerListaIndicadores();
    }

    @Override
    public List<Indicador> obtenerIndicadorProceso(int idProceso) {
        return indicadorRepo.obtenerIndicadorProceso(idProceso);
    }
}
