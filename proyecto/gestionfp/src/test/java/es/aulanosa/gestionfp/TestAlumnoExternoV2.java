package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.service.AlumnoExternoService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestAlumnoExternoV2 {

    @Autowired
    private AlumnoExternoService alumnoExternoService;


    @Test
    @Order(1)
    void buscarPorEstado() throws NoSeHaEncontradoException {
        var a = alumnoExternoService.buscarPorEstado();
        if (a != null) {
            System.out.println(a);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado");
        }
    }


}
