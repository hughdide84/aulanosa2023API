package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Proyectos;
import es.aulanosa.gestionfp.service.ProyectosService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class ProyectosTest1 {
    @Autowired
    private ProyectosService proyectosService;

    @Test()
    @Order(1)
    void insertarProyecto() {
        Proyectos proyectos = new Proyectos();
        proyectos.setIdAlumno(52);
        proyectos.setDocumento('a');
        proyectos.setPresentacion('e');
        proyectos.setNotaDoc(9);
        proyectos.setNotaPres(5);
        proyectos.setNotaFinal(6);
        var a = proyectosService.save(proyectos);
        System.out.println(a);
    }

    @Test
    @Order(2)
    void consultarProyectoPorId() {
        var a = proyectosService.findById(5);
        System.out.println(a);
    }

    @Test
    @Order(3)
    void updateProyectos() {
        Proyectos proyectos = new Proyectos();
        proyectos.setId(56);
        proyectos.setIdAlumno(23);
        proyectos.setDocumento('a');
        proyectos.setPresentacion('e');
        proyectos.setNotaDoc(9);
        proyectos.setNotaPres(5);
        proyectos.setNotaFinal(6);
        Proyectos mejora = null;
        try {
            mejora = proyectosService.update(proyectos);
            System.out.println(mejora);
        }
        catch (NoSeHaEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    void consultarAllProyectos() {
        var a = proyectosService.findAll();
        System.out.println(a);
    }

    @Test
    @Order(5)
    void borrarProyecto() {
        Optional<Proyectos> proyectosCon = proyectosService.findById(13);
        if (proyectosCon != null) {
            proyectosService.deleteById(13);
            if (proyectosService.findById(13) == null) {
                System.out.println("Proyecto eliminado");
            }
            else {
                System.out.println("Proyecto no eliminado");
            }
        }
        else {
            System.out.println("Proyecto no encontrado");
        }
    }
}
