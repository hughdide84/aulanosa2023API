package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.service.AlumnoService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Optional;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestAlumnoV3 {

    @Autowired
    private AlumnoService service;

    @Test
    @Order(1)
    void findX() {

        var a = service.buscarPorCursoEstudioEstado(3, 3);
        System.out.println(a);
    }


}
