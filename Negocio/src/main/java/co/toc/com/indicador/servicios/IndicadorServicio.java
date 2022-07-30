package co.toc.com.indicador.servicios;

import co.toc.com.indicador.entidades.Indicador;

import java.util.List;

public interface IndicadorServicio {


    Indicador registrarIndicador(Indicador indicador);

    Indicador obtenerIndicador (Integer id);

    void inactivarIndicador(Indicador indicador);

    List<Indicador> obtenerListaIndicadores();

}
