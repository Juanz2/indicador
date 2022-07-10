package co.toc.com.indicador.servicios;

import co.toc.com.indicador.entidades.Usuario;
import org.hibernate.cfg.annotations.ListBinder;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario (Usuario usuario, int tipoOperacion) throws Exception;

    Usuario obtenerUsuario (Integer id) throws Exception;

    Usuario obtenerUsuarioLogin (String email, String password) throws Exception;
    void inactivarUsuario (Usuario usuario);

    List<Usuario> obtenerUsuarios();

    void recuperarPassword(String email) throws Exception;
}
