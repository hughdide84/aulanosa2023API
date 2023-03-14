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
            var a = asignaturaService.save(asignatura);
            System.out.println(a);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha encontrado el usuario", e);
        }
    }

    @Test
    @Order(2)
    public void consultarPorId() throws NoSeHaEncontradoException {

        var a = asignaturaService.findById(1);
        System.out.println(a);

    }

    @Test
    @Order(3)
    public void update() throws NoSeHaEncontradoException {

        var asignaturaConsultada = asignaturaService.findById(1);
        if (asignaturaConsultada != null) {
            asignaturaConsultada.setNombre("Programación333");
            try {
                var a = asignaturaService.update(asignaturaConsultada);
                System.out.println(a);
            } catch (Exception e) {
                throw new NoSeHaEncontradoException("No se ha encontrado el usuario", e);
            }
        }
    }

    @Test
    @Order(4)
    public void delete() throws NoSeHaEncontradoException {
        var asignaturaConsultada = asignaturaService.findById(1);
        if (asignaturaConsultada != null) {
            try {
                asignaturaService.deleteById(asignaturaConsultada.getId());
                System.out.println("Se ha borrado correctamente");
            } catch (Exception e) {
                throw new NoSeHaEncontradoException("No se ha encontrado el usuario", e);
            }
        }
    }

    @Test
    @Order(5)
    public void findAll() throws NoSeHaEncontradoException {
        try {
            List<Asignatura> asignaturas = asignaturaService.findAll();
            System.out.println(asignaturas);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha encontrado el usuario", e);
        }
    }

    @Test
    @Order(6)
    public void findAllByNombre() throws NoSeHaEncontradoException {
        try {
            List<Asignatura> asignaturas = asignaturaService.findAllByNombre("Programación");
            System.out.println(asignaturas);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha encontrado el usuario", e);
        }
    }


}
