package co.toc.com.indicador.servicios;

import co.toc.com.indicador.entidades.RegistroIndicador;

import java.util.List;

public interface RegistroIndicadorServicio {

    RegistroIndicador registarIndicador(RegistroIndicador registroIndicador);

    RegistroIndicador obtenerRegistroIndicador(Integer id);

    void inactivarRegistroIndicador(RegistroIndicador registroIndicador);

    List<RegistroIndicador> obtenerRegistroIndicadores();

    List<RegistroIndicador> obtenerRegistroIndicadorProceso(int idProceso);

    List<RegistroIndicador> obtenerRegistroIndicador(int idIndicador);

}