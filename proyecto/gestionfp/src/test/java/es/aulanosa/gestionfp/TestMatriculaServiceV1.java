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
import java.util.Optional;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestMatriculaServiceV1 {

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
            var a = matriculasService.insertarMatricula(matricula);
            System.out.println(a);
        }
        catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha podido ejecutar el insertado");
        }
    }

    @Test
    @Order(2)
    void consultarMatriculasPorId() {
        var a = matriculasService.consultarPorIdMatricula(32);
        System.out.println(a);
    }

    @Test
    @Order(3)
    void modificarMatricula() throws NoSeHaEncontradoException {

        Optional<Matricula> a = matriculasService.consultarPorIdMatricula(58);

        if (a.isPresent()) {
            a.get().setNombre("NombreModificado");
             matriculasService.insertarMatricula(a.get());
            System.out.println(a);
        }

        else {
            throw new NoSeHaEncontradoException("No se ha encontrado la matricula");
        }
    }

    @Test
    @Order(4)
    void consultarMatriculaModificadaPorId() {
        var a = matriculasService.consultarPorIdMatricula(32);
        System.out.println(a);
    }

    @Test
    @Order(5)
    void eliminarMatricula() throws NoSeHaEncontradoException {
        var a = matriculasService.consultarPorIdMatricula(1);

        if (a.isPresent()) {
            matriculasService.eliminarMatricula(a.get().getId());
            System.out.println("Eliminado");
        }

        else {
            throw new NoSeHaEncontradoException("La matricula buscada no existe");
        }

    }

    @Test
    @Order(6)
    void consultarTodos() {
        List<Matricula> a = matriculasService.consultarTodasMatriculas();
        System.out.println(a);
    }





    @Test
    @Order(7)
    void buscarMatriculaPorNombre() {
        List<Matricula> a = matriculasService.buscarPorNombreDeMatricula("No");
        System.out.println(a);
    }

    @Test
    @Order(8)
    void buscarCursosDeMatricula() {
        List<Matricula> matriculasPorIdCurso = matriculasService.buscarTodosCursosPorId(23);
        System.out.println(matriculasPorIdCurso);
    }
    @Test
    @Order(9)
    void buscarMatriculaPorMes() {
        List<Matricula> a = matriculasService.buscarPorMesDeMatricula(3);
        System.out.println(a);
    }


}
