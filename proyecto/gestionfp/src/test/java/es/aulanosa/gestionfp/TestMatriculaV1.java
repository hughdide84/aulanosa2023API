package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Matricula;
import es.aulanosa.gestionfp.service.MatriculasService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestMatriculaV1 {

    @Autowired
    MatriculasService matriculasService;

    @Test
    @Order(1)
    void insertarMatricula() throws NoSeHaEncontradoException {
        Matricula matricula = new Matricula();
        matricula.setFactura("Ninguna");
        matricula.setNombre("No");
        matricula.setNif("No");
        matricula.setCuota(56);
        matricula.setMatricula(236);
        matricula.setIdCurso(23);
        matricula.setObservaciones("Observado");
        matricula.setIdUsuario(14);
        Timestamp timestamp = new Timestamp(122,2,30,16,30,21,1);
        matricula.setFecha(timestamp);
        try {
            var a = matriculasService.insertar(matricula);
            System.out.println(a);
        }
        catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha podido ejecutar el insertado");
        }
    }

    @Test
    @Order(2)
    void consultarMatriculasPorId() {
        var a = matriculasService.consultarPorId(32);
        System.out.println(a);
    }

    @Test
    @Order(3)
    void consultarTodos() {
        List<Matricula> a = matriculasService.consultarTodos();
        System.out.println(a);
    }

    @Test
    @Order(4)
    void modificarMatricula() throws NoSeHaEncontradoException {

        var a = matriculasService.consultarPorId(32);

        if (a.isPresent()) {
            Matricula matricula = new Matricula();
            matricula.setId(32);
            matricula.setFactura("Alguna");
            matricula.setNombre("Alguni");
            matricula.setNif("Si");
            matricula.setCuota(45);
            matricula.setMatricula(4165);
            matricula.setIdCurso(41);
            matricula.setObservaciones("No observado");
            matricula.setIdUsuario(14);
            Timestamp timestamp = new Timestamp(102,5,15,16,30,21,1);
            matricula.setFecha(timestamp);

            var g = matriculasService.insertar(matricula);
            System.out.println(g);
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
        List<Matricula> a = matriculasService.buscarPorNombreDeMatricula("No");
        System.out.println(a);
    }

    @Test
    @Order(7)
    void buscarCursosDeMatricula() {
        List<Curso> a = matriculasService.buscarTodosCursosPorId(23);
        System.out.println(a);
    }
    @Test
    @Order(7)
    void buscarMatriculaPorMes() {
        List<Matricula> a = matriculasService.bu(23);
        System.out.println(a);
    }


}
