package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUsuarioServicev1 {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    void insertarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Pepe");
        usuario.setPassword("Pérez");
        usuario.setRol("ROLE_ADMIN");
        usuario.setEmail("pepe@gmial.com");
        var a = usuarioService.save(usuario);
        System.out.println(a);
    }

    @Test
    void consultarUsuarioPorId() {
        var a = usuarioService.findById(2);
        System.out.println(a);
    }

    @Test
    void updateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(2);
        usuario.setNombre("AAA");
        usuario.setPassword("Pérez");
        usuario.setRol("ROLE_ADMIN");
        usuario.setEmail("nada@nada");
        var a = usuarioService.save(usuario);
        System.out.println(a);
    }

    @Test
    void consultarTodosUsuarios() {
        var a = usuarioService.findAll();
        System.out.println(a);
    }

    @Test
    void borrarUsuario() {
        usuarioService.deleteById(2);
    }

    @Test
    void consultarUsuarioPorNombre() {
        var a = usuarioService.findAllByNombre("Pepe");
        System.out.println(a);
    }
}
