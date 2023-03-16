package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.MensajeUsuario;
import es.aulanosa.gestionfp.service.MensajeUsuarioService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestMensajeUsuarioV1 {

    @Autowired
    private MensajeUsuarioService mensajeUsuarioService;

    @Test
    @Order(1)
    void insertarMensajeUsuario() throws NoSeHaEncontradoException {
        MensajeUsuario mensajeUsuario = new MensajeUsuario();
        mensajeUsuario.setIdMensaje(5);
        mensajeUsuario.setIdUsuario(14);
        mensajeUsuario.setEstado('p');

        try {
            mensajeUsuarioService.insertar(mensajeUsuario);
            System.out.println(mensajeUsuario);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha encontrado el mensajeUsuario");
        }
    }

    @Test
    @Order(2)
    void consultarMensajeUsuarioPorId() throws NoSeHaEncontradoException {
        var a = mensajeUsuarioService.consultarPorId(1);
        if (a.isPresent()) {
            System.out.println(a);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el mensajeUsuario");
        }
    }

    @Test
    @Order(3)
    void modificarMensajeUsuario() throws NoSeHaEncontradoException {
        MensajeUsuario mensajeUsuario = new MensajeUsuario();
        mensajeUsuario.setId(1);
        mensajeUsuario.setIdMensaje(4);
        mensajeUsuario.setIdUsuario(14);
        mensajeUsuario.setEstado('l');
        try {
            mensajeUsuarioService.modificar(mensajeUsuario);
            System.out.println(mensajeUsuario);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha encontrado el mensajeUsuario");
        }
    }

    @Test
    @Order(4)
    void consultarTodosMensajeUsuario() throws NoSeHaEncontradoException {
        var a = mensajeUsuarioService.consultarTodos();

        System.out.println(a);

    }

    @Test
    @Order(5)
    void eliminarMensajeUsuario() throws NoSeHaEncontradoException {
        try {
            mensajeUsuarioService.eliminar(1);
            System.out.println("Se ha eliminado el mensajeUsuario");
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha encontrado el mensajeUsuario");
        }
    }

    @Test
    @Order(6)
    void consultarMensajeUsuarioPorIdMensaje() throws NoSeHaEncontradoException {
        var a = mensajeUsuarioService.listarPorDestinario(5);
        System.out.println(a);

    }

    @Test
    @Order(7)
    void consultarMensajeUsuarioPorIdUsuario() throws NoSeHaEncontradoException {
        var a = mensajeUsuarioService.listarPorAutor(14);
        System.out.println(a);

    }

    
}
