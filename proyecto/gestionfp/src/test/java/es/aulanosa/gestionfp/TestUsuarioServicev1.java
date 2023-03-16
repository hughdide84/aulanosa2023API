package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.UsuarioService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestUsuarioServicev1 {

    @Autowired
    private UsuarioService usuarioService;

    @Test()
    @Order(1)
    void insertarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Pepe");
        usuario.setPassword("Pérez");
        usuario.setRol("ROLE_ADM");
        usuario.setEmail("pepe@gmial.com");
        Usuario usuarioCreado = null;
        try {
            usuarioCreado = usuarioService.crear(usuario);
            System.out.println(usuarioCreado);
        } catch (NoSeHaEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(2)
    void consultarUsuarioPorId() {
        var usuarioConsultado = usuarioService.listarPorId(17);
        System.out.println(usuarioConsultado);
    }

    @Test
    @Order(3)
    void consultarUsuarioPorNombre() {
        var usuarioConsultado = usuarioService.consultarPorNombre("Pepe");
        System.out.println(usuarioConsultado);
    }

    @Test
    @Order(3)
    void updateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(20);
        usuario.setNombre("BBB");
        usuario.setPassword("Pérez");
        usuario.setRol("ROLE_ADMIN");
        usuario.setEmail("nada@nada");
        Usuario a = null;
        try {
            a = usuarioService.actualizar(usuario);
            System.out.println(a);
        } catch (NoSeHaEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    void consultarTodosUsuarios() {
        var a = usuarioService.listarTodo();
        System.out.println(a);
    }

    @Test
    @Order(5)
    void borrarUsuario() {
        Optional<Usuario> usuarioConsultado = usuarioService.listarPorId(15);
        if (!usuarioConsultado.isPresent()) {
            usuarioService.borrarPorId(15);
            if (usuarioService.listarPorId(15) == null)
                System.out.println("Usuario borrado");
            else
                System.out.println("Usuario no borrado");
        } else {
            System.out.println("Usuario no encontrado");
        }
    }
}
