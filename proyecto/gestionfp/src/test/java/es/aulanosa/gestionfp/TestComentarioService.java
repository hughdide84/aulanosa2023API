package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Comentario;
import es.aulanosa.gestionfp.service.ComentarioService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestComentarioService {

    @Autowired
    private ComentarioService comentarioService;

    @Test()
    @Order(1)
    void crearComentario() {
        Comentario comentario = new Comentario();
        comentario.setSistema('C');
        comentario.setReferencia(7);
        comentario.setTexto("ROLE_ADM");
        comentario.setIdUsuarioComentario(69);
        comentario.setFecha(new Date(2023, 2, 14));
        var comentarioCreado = comentarioService.crear(comentario);
        System.out.println(comentarioCreado);
    }

    @Test
    @Order(2)
    void consultarComentarioPorId() {
        var comentarioConsultado = comentarioService.listarPorId(14);
        System.out.println(comentarioConsultado);
    }

    @Test
    @Order(3)
    void actualizarComentario() {
        Comentario comentario = new Comentario();
        comentario.setId(77);
        comentario.setSistema('A');
        comentario.setReferencia(21);
        comentario.setTexto("ROLE_ADMIN");
        comentario.setIdUsuarioComentario(14);
        comentario.setFecha(new Date(2023, 2, 14));
        Comentario comentarioActualizado = null;
        try {
            comentarioActualizado = comentarioService.actualizar(comentario);
            System.out.println(comentarioActualizado);
        } catch (NoSeHaEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    void consultarTodosComentarios() {
        var c = comentarioService.listarTodo();
        System.out.println(c);
    }

    @Test
    @Order(5)
    void borrarComentarioPorId() {
        Integer id = 15;
        Optional<Comentario> comentarioConsultado = comentarioService.listarPorId(id);
        if (comentarioConsultado.isPresent()) {
            comentarioService.borrarPorId(id);
            if (comentarioService.listarPorId(id).isPresent()) {
                System.out.println("Comentario no borrado");
            } else {
                System.out.println("Comentario borrado");
            }
        } else {
            System.out.println("Comentario no encontrado");
        }
    }

    @Test
    @Order(6)
    void consultarComentarioPorSistemaYReferencia() {
        var comentariosConsultados = comentarioService.listarPorSistemaYReferencia('A', 21);
        System.out.println(comentariosConsultados);
    }

    @Test
    @Order(7)
    void listarEventosPorUsuario() {
        var eventos = comentarioService.listarEventosPorUsuario(65);
        System.out.println(eventos);
    }
}