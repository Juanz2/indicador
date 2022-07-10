package co.toc.com.indicador.servicios;

import co.toc.com.indicador.entidades.Usuario;
import co.toc.com.indicador.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicioImp implements UsuarioServicio {


    private final UsuarioRepo usuarioRepo;
    private final EmailServicio emailServicio;

    public UsuarioServicioImp(UsuarioRepo usuarioRepo, EmailServicio emailServicio) {
        this.usuarioRepo = usuarioRepo;
        this.emailServicio = emailServicio;
    }


    @Override
    public Usuario registrarUsuario(Usuario usuario, int tipoOperacion) throws Exception {

        // Creación
        if (tipoOperacion == 1) {
            if (usuarioRepo.obtenerUsuarioEmail(usuario.getEmail()) != null) {
                throw new Exception("El usuario " + usuario.getEmail() + " ya se encuentra registrado");
            } else {
                usuarioRepo.save(usuario);
            }
        } else {
            usuarioRepo.save(usuario);
        }
        return null;
    }

    @Override
    public Usuario obtenerUsuario(Integer id) throws Exception {

        Usuario usuario = usuarioRepo.obtenerUsuarioId(id);
        if (usuario == null)
            throw new Exception("El id del usuario no fue encontrado");
        return usuario;
    }

    @Override
    public Usuario obtenerUsuarioLogin(String email, String password) throws Exception {
        Usuario usuario = usuarioRepo.obtenerUsuarioLogin(email, password);
        if (usuario == null)
            throw new Exception("Usuario o contraseña incorrecta");
        else if (usuario.getEstado().equals("I"))
            throw new Exception("El usuario se encuentra inactivo");

        return usuario;
    }

    @Override
    public void inactivarUsuario(Usuario usuario) {

        usuario.setEstado("I");
        usuarioRepo.save(usuario);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepo.obtenerUsuarios();
    }

    @Override
    public void recuperarPassword(String email) throws Exception{

        Usuario usuario = usuarioRepo.obtenerUsuarioEmail(email);
        if(usuario == null)
            throw new Exception("Correo electrónico no encontrado");
        String cuerpoMensaje = "Para recuperar tu contraseña ingresa al siguiente enlace: http://localhost:8080/cambiarPassword.xhtml?usuario=" + usuario.getIdUsuario();
        emailServicio.EnviarEmail("Recuperación de contraseña", cuerpoMensaje, email);
    }
}
