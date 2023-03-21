package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Asignatura;
import es.aulanosa.gestionfp.service.AsignaturaService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestAsignatura {

    @Autowired
    private AsignaturaService asignaturaService;

    @Test
    @Order(1)
    public void insertar() throws NoSeHaEncontradoException {

        Asignatura asignatura = new Asignatura();
        asignatura.setNombre("Programación");
        asignatura.setIdCurso(1);
        asignatura.setIdEstudios(3);
        asignatura.setNivel(1);
        try {
            var a = asignaturaService.guardarAsignatura(asignatura);
            System.out.println(a);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha encontrado la asignatura", e);
        }
    }

    @Test
    @Order(2)
    public void consultarPorId() throws NoSeHaEncontradoException {

        var a = asignaturaService.buscarPorIdAsignatura(1);
        System.out.println(a);

    }

    @Test
    @Order(3)
    public void modificar() throws NoSeHaEncontradoException {

        var asignaturaConsultada = asignaturaService.buscarPorIdAsignatura(1032);
        if (asignaturaConsultada != null) {
            asignaturaConsultada.setNombre("Programación333");
            try {
                var a = asignaturaService.modificarAsignatura(asignaturaConsultada);
                System.out.println(a);
            } catch (Exception e) {
                throw new NoSeHaEncontradoException("No se ha encontrado la asignatura", e);
            }
        } else {
            System.out.println("No se ha encontrado la asignatura");
        }
    }

    @Test
    @Order(4)
    public void borrar() throws NoSeHaEncontradoException {
        var asignaturaConsultada = asignaturaService.buscarPorIdAsignatura(1);
        if (asignaturaConsultada != null) {
            try {
                asignaturaService.borrarPorIdAsignatura(asignaturaConsultada.getId());
                System.out.println("Se ha borrado correctamente");
            } catch (Exception e) {
                throw new NoSeHaEncontradoException("No se ha encontrado la asignatura", e);
            }
        } else {
            System.out.println("No se ha encontrado la asignatura");
        }
    }

    @Test
    @Order(5)
    public void buscarTodo() throws NoSeHaEncontradoException {
        try {
            List<Asignatura> asignaturas = asignaturaService.buscarTodoAsignatura();
            System.out.println(asignaturas);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No se han encontrado asignaturas", e);
        }
    }

    @Test
    @Order(6)
    public void buscarTodosPorNombreCon() throws NoSeHaEncontradoException {
        try {
            List<Asignatura> asignaturas = asignaturaService.buscarTodoPorNombreAsignaturaConteniendoNombreAsignatura("Programación");
            System.out.println(asignaturas);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha encontrado el asignaturas", e);
        }
    }


}
