package es.aulanosa.gestionfp;

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
    void insertarComentario() {
        Comentario comentario = new Comentario();
        comentario.setSistema('P');
        comentario.setReferencia(7);
        comentario.setTexto("ROLE_ADM");
        comentario.setFecha(new Date(2023, 2, 14));
        var c = comentarioService.save(comentario);
        System.out.println(c);
    }

    @Test
    @Order(2)
    void consultarComentarioPorId() {
        var c = comentarioService.findById(1);
        System.out.println(c);
    }

    @Test
    @Order(3)
    void updateComentario() {
        Comentario comentario = new Comentario();
        comentario.setId(77);
        comentario.setSistema('A');
        comentario.setReferencia(21);
        comentario.setTexto("ROLE_ADMIN");
        comentario.setFecha(new Date(2023, 2, 14));
        Comentario c = null;
        c = comentarioService.update(comentario);
        System.out.println(c);
    }

    @Test
    @Order(4)
    void consultarTodosComentarios() {
        var c = comentarioService.findAll();
        System.out.println(c);
    }

    @Test
    @Order(5)
    void borrarComentario() {
        Optional<Comentario> comentarioConsultado = comentarioService.findById(15);
        if (comentarioConsultado.isPresent()) {
            comentarioService.deleteById(15);
            if (comentarioService.findById(15) == null)
                System.out.println("Comentario borrado");
            else
                System.out.println("Comentario no borrado");
        } else {
            System.out.println("Comentario no encontrado");
        }
    }

    @Test
    @Order(6)
    void consultarComentarioPorSistemaYReferencia() {
        var c = comentarioService.findBySistemaAndReferencia('A', 21);
        System.out.println(c);
    }
}
