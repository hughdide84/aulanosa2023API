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
public class TestAlumnoServiceV1 {

    @Autowired
    private AlumnoService service;

    @Test()
    @Order(1)
    void insertarAlumno() {
        Alumno alumno = new Alumno();
        alumno.setIdCurso(1);
        alumno.setIdEstudios(3);
        alumno.setNombre("alumnoNuevo");
        alumno.setCv('a');
        alumno.setCarta('b');
        alumno.setIdEmpresa(100);
        alumno.setInicioPr(new Timestamp(new GregorianCalendar(2014, 3, 24).getTimeInMillis()));
        alumno.setFinPr(new Timestamp(new GregorianCalendar(2016, 2, 1).getTimeInMillis()));

        var a = service.guardarAlumno(alumno);
        System.out.println(a);

    }

    @Test
    @Order(2)
    void bucarPorId(){

        System.out.println(service.buscarPorId(3));

    }

    @Test
    @Order(3)
    void buscarTodo(){

        System.out.println(service.buscarTodo());

    }

    @Test
    @Order(4)
    void modificarAlumno() throws NoSeHaEncontradoException {
        Optional<Alumno> optionalAlumno = service.buscarPorId(4);

        if(optionalAlumno.isPresent()){
            optionalAlumno.get().setNombre("AlumnoMOD");
            Alumno alumno1 = service.modificarAlumno(optionalAlumno.get());

            System.out.println("Alumno modificado a: " + optionalAlumno);
        }else{
            throw new NoSeHaEncontradoException("No se ha encontrado el alumno especificado");
        }
    }
    @Test
    @Order(5)
    void eliminarAlumno(){

        try{
            service.eliminarAlumno(3);
            System.out.println("Alumno eliminado con exito");

        }catch (DataIntegrityViolationException e){
            System.out.println("No se puede eliminar porque existen relaciones con la entidad");
        }catch (NoSeHaEncontradoException e){
            System.out.println("No se ha encontrado el alumno");
        }

    }

}
