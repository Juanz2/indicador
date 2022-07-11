package co.toc.com.indicador.repositorios;

import co.toc.com.indicador.entidades.Proceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcesoRepo extends JpaRepository<Proceso, Integer> {

    @Query("select p from Proceso p where p.estado = 'A'")
    List<Proceso> obtenerProcesos();
}