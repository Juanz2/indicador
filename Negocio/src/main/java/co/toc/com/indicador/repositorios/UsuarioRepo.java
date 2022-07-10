package co.toc.com.indicador.repositorios;

import co.toc.com.indicador.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {


    @Query("select u from Usuario u where u.email = :email and u.estado = 'A'")
    Usuario obtenerUsuarioEmail(String email);

    @Query("select u from Usuario u where u.idUsuario =  :idUsuario and u.estado = 'A'")
    Usuario obtenerUsuarioId(Integer idUsuario);


    @Query("select u from Usuario u where u.estado = 'A'")
    List<Usuario> obtenerUsuarios();

    @Query("select u from Usuario u where u.email = :email and u.password = :password")
    Usuario obtenerUsuarioLogin (String email, String password);
}
