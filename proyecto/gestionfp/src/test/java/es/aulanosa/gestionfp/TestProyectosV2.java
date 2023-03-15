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
        var a = proyectosService.guardar(proyectos);
        System.out.println(a);
    }

    @Test
    @Order(2)
    void consultarProyectoPorId() {
        var a = proyectosService.buscarPorId(5);
        System.out.println(a);
    }

    @Test
    @Order(3)
    void modificarProyectos() throws NoSeHaEncontradoException{
        Proyectos proyectos = new Proyectos();
        proyectos.setId(20);
        proyectos.setIdAlumno(3);
        proyectos.setDocumento('l');
        proyectos.setPresentacion('k');
        proyectos.setNotaDoc(10);
        proyectos.setNotaPres(10);
        proyectos.setNotaFinal(10);

        Proyectos mejora = proyectosService.buscarPorId(proyectos.getId());
        Proyectos mejora2 = null;

        if (mejora != null) {
            mejora2 = proyectosService.modificar(proyectos);
            System.out.println(mejora2);
        }
        else {
            System.out.println("No se ha encontrado el proyecto a modificar");
        }
    }

    @Test
    @Order(4)
    void consultarTodosProyectos() {
        var a = proyectosService.buscarTodo();
        System.out.println(a);
    }

    @Test
    @Order(5)
    void borrarProyecto() {
        Proyectos proyectosCon = proyectosService.buscarPorId(350);
        if (proyectosCon != null) {

            int idCom = proyectosCon.getId();
            proyectosService.borrar(proyectosCon.getId());
            Proyectos proyectosComprobar = proyectosService.buscarPorId(idCom);

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
