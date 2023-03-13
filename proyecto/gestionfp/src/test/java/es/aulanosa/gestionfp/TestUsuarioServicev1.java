package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.UsuarioService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        usuario.setRol("ROLE_ADMIN");
        usuario.setEmail("pepe@gmial.com");
        var a = usuarioService.save(usuario);
        System.out.println(a);
    }

    @Test
    @Order(2)
    void consultarUsuarioPorId() {
        var a = usuarioService.findById(1);
        System.out.println(a);
    }

    @Test
    @Order(3)
    void updateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("AAA");
        usuario.setPassword("Pérez");
        usuario.setRol("ROLE_ADMIN");
        usuario.setEmail("nada@nada");
        var a = usuarioService.save(usuario);
        System.out.println(a);
    }

    @Test
    @Order(4)
    void consultarTodosUsuarios() {
        var a = usuarioService.findAll();
        System.out.println(a);
    }

    @Test
    @Order(5)
    void borrarUsuario() {
        usuarioService.deleteById(1);
        if (usuarioService.findById(1) == null)
            System.out.println("Usuario borrado");
        else
            System.out.println("Usuario no borrado");
    }

    @Test
    @Order(6)
    void consultarUsuarioPorNombre() {
        var a = usuarioService.findAllByNombre("Pepe");
        System.out.println(a);
    }
}
