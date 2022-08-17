package co.toc.com.indicador.repositorios;

import co.toc.com.indicador.entidades.RegistroIndicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroIndicadorRepo extends JpaRepository<RegistroIndicador, Integer> {


    @Query("select ri from RegistroIndicador ri where  ri.estado = 'A' order by ri.mes asc")
    List<RegistroIndicador> obtenerListaRegistroIndicador();

    @Query("select ri from RegistroIndicador  ri join ri.indicador ind join ind.proceso  p " +
            "where p.idProceso = :idProceso and ri.estado = 'A' order by ind.nombre asc")
    List<RegistroIndicador> obtenerRegistroIndicadorProceso(int idProceso);

    @Query("select ri from RegistroIndicador  ri join ri.indicador ind  where ind.idIndicador = :idIndicador and ri.estado = 'A' order by ri.mes asc")
    List<RegistroIndicador> obtenerRegistroIndicador(int idIndicador);

    @Query("select ri from RegistroIndicador  ri join ri.indicador ind where ri.mes = :mes " +
            "and ind.idIndicador = :indicador and ri.anio = :anio and ri.estado = 'A'")
    List<RegistroIndicador> obtenerIndicadorValidar(String mes, int indicador, String anio);
}
