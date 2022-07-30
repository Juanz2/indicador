package co.toc.com.indicador.repositorios;

import co.toc.com.indicador.entidades.Indicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndicadorRepo extends JpaRepository<Indicador, Integer> {

    @Query("select i from Indicador  i where i.estado = 'A'")
    List<Indicador> obtenerListaIndicadores();

    @Query("select i from Indicador i join i.proceso p where p.idProceso = :idProceso and p.estado = 'A'")
    List<Indicador> obtenerIndicadorProceso(int idProceso);
}
