package co.toc.com.indicador.repositorios;

import co.toc.com.indicador.entidades.RegistroIndicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroIndicadorRepo extends JpaRepository<RegistroIndicador, Integer> {


    @Query("select ri from RegistroIndicador ri where  ri.estado = 'A'")
    List<RegistroIndicador> obtenerListaRegistroIndicador();
}
