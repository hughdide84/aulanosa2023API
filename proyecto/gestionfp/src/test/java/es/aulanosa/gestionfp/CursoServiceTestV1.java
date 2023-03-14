package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.service.CursoService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Optional;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class CursoServiceTestV1 {

    //pruebas de cada metodo a implementar
    @Autowired
    private CursoService service;

    @Test
    @Order(1)
    void insertar(){
        Curso curso = new Curso();
        curso.setNombre("curso2");
        curso.setInicio(new Timestamp(new GregorianCalendar(2020, 3, 24).getTimeInMillis()));
        curso.setFin(new Timestamp(new GregorianCalendar(2022, 8, 24).getTimeInMillis()));
        curso.setEstado('b');

        var var1 = service.insertar(curso);

        System.out.println(var1);
    }
    @Test
    @Order(2)
    void eliminar(){
        Curso curso = new Curso();
        curso.setId(2);
        curso.setNombre("curso1");
        curso.setInicio(new Timestamp(new GregorianCalendar(2014, 3, 24).getTimeInMillis()));
        curso.setFin(new Timestamp(new GregorianCalendar(2016, 8, 24).getTimeInMillis()));
        curso.setEstado('a');


        service.insertar(curso);
        service.eliminar(curso.getId());

        System.out.println(curso);
    }

    @Test
    @Order(3)
    void modificar() throws NoSeHaEncontradoException {
        Optional<Curso> optionalCurso = Optional.ofNullable(service.buscarPorId(1));

        if(optionalCurso.isPresent()){
            optionalCurso.get().setNombre("CursoMOD");
            Curso curso1 = service.modificar(optionalCurso.get());

            System.out.println("Curso modificado a: " + curso1);
        }else{
            throw new NoSeHaEncontradoException("No se ha encontrado el curso especificado");
        }
    }
    @Test
    @Order(4)
    void buscarTodo(){
        Curso curso = new Curso();
        curso.setId(2);
        curso.setNombre("curso1");
        curso.setInicio(new Timestamp(new GregorianCalendar(2014, 3, 24).getTimeInMillis()));
        curso.setFin(new Timestamp(new GregorianCalendar(2016, 8, 24).getTimeInMillis()));
        curso.setEstado('a');


        System.out.println(service.buscarTodo());

    }
    @Test
    @Order(5)
    void bucarPorId(){
        Curso curso = new Curso();
        curso.setId(3);
        curso.setNombre("curso1");
        curso.setInicio(new Timestamp(new GregorianCalendar(2014, 3, 24).getTimeInMillis()));
        curso.setFin(new Timestamp(new GregorianCalendar(2016, 8, 24).getTimeInMillis()));
        curso.setEstado('a');


        System.out.println(service.buscarPorId(3));

    }

}
