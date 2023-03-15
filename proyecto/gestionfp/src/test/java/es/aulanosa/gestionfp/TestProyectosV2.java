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


@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestProyectosV2 {
    @Autowired
    private ProyectosService proyectosService;

    @Test()
    @Order(1)
    void insertarProyecto() {
        Proyectos proyectos = new Proyectos();
        proyectos.setIdAlumno(3);
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
        proyectos.setId(14);
        proyectos.setIdAlumno(3);
        proyectos.setDocumento('l');
        proyectos.setPresentacion('k');
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
        Proyectos proyectosCon = proyectosService.findById(350);
        if (proyectosCon != null) {

            int idCom = proyectosCon.getId();
            proyectosService.deleteById(proyectosCon.getId());
            Proyectos proyectosComprobar = proyectosService.findById(idCom);

            if (proyectosComprobar == null) {
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
