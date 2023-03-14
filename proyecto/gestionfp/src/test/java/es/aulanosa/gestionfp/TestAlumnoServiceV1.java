package es.aulanosa.gestionfp;


import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.service.AlumnoService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestAlumnoServiceV1 {

    @Autowired
    private AlumnoService service;

    @Test()
    @Order(1)
    void insertarUsuario() {
        Alumno alumno = new Alumno();
        alumno.setIdCurso(1);
        alumno.setIdEstudios(3);
        alumno.setNombre("alumnoNuevo");
        alumno.setCv('a');
        alumno.setCarta('b');
        alumno.setIdEmpresa(100);
        alumno.setInicioPr(new Timestamp(new GregorianCalendar(2014, 3, 24).getTimeInMillis()));
        alumno.setFinPr(new Timestamp(new GregorianCalendar(2016, 2, 1).getTimeInMillis()));

        var a = service.guardar(alumno);
        System.out.println(a);

    }

}
