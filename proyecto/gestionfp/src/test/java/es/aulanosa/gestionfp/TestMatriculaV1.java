package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Matriculas;
import es.aulanosa.gestionfp.service.MatriculasService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestMatriculaV1 {

    @Autowired
    MatriculasService matriculasService;

    @Test
    @Order(1)
    void insertarMatricula() throws NoSeHaEncontradoException {
        Matriculas matriculas = new Matriculas();
        matriculas.setFactura("Ninguna");
        matriculas.setNombre("No");
        matriculas.setNif("No");
        matriculas.setCuota(56);
        matriculas.setMatricula(236);
        matriculas.setIdCurso(23);
        matriculas.setObservaciones("Observado");
        matriculas.setIdUsuario(14);
        Timestamp timestamp = new Timestamp(1956,2,30,16,30,21,1);
        matriculas.setFecha(timestamp);
        try {
            var a = matriculasService.insertar(matriculas);
        }
        catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha podido ejecutar el insertado");
        }
    }

    @Test
    @Order(2)
    void consultarMatriculasPorId() {
        var a = matriculasService.consultarPorId(1);
        System.out.println(a);
    }

    @Test
    @Order(3)
    void consultarTodos() {
        var a = matriculasService.consultarTodos();
        System.out.println(a);
    }

    @Test
    @Order(4)
    void modificarMatricula() throws NoSeHaEncontradoException {

        var a = matriculasService.consultarPorId(1);

        if (a.isPresent()) {
            Matriculas matriculas = new Matriculas();
            matriculas.setId(1);
            matriculas.setFactura("Alguna");
            matriculas.setNombre("Alguni");
            matriculas.setNif("Si");
            matriculas.setCuota(45);
            matriculas.setMatricula(4165);
            matriculas.setIdCurso(41);
            matriculas.setObservaciones("No observado");
            matriculas.setIdUsuario(14);
            Timestamp timestamp = new Timestamp(1906,5,15,16,30,21,1);
            matriculas.setFecha(timestamp);

            var g = matriculasService.insertar(matriculas);
        }

        else {
            throw new NoSeHaEncontradoException("No se ha encontrado la matricula");
        }
    }

    @Test
    @Order(5)
    void eliminarMatricula() throws NoSeHaEncontradoException {
        var a = matriculasService.consultarPorId(1);

        if (a != null) {
            matriculasService.eliminar(1);
            System.out.println("Eliminado");
        }

        else {
            throw new NoSeHaEncontradoException("La matricula buscada no existe");
        }

    }

    @Test
    @Order(6)
    void buscarMatriculaPorNombre() {
        var a = matriculasService.buscarPorNombreDeMatricula("Ninguna");
        System.out.println(a);
    }

    @Test
    @Order(7)
    void buscarCursosDeMatricula() {
        var a = matriculasService.buscarTodosCursosPorId(23);
        System.out.println(a);
    }

}
