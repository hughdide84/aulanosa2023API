package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Comentario;
import es.aulanosa.gestionfp.model.Empresa;
import es.aulanosa.gestionfp.model.Entregable;
import es.aulanosa.gestionfp.service.EmpresaService;
import es.aulanosa.gestionfp.service.EntregableService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestEntregableServiceV1 {

    @Autowired
    private EntregableService service;


    @Test
    @Order(1)
    void insertarEntregable() throws NoSeHaEncontradoException {
        Entregable entregable = new Entregable();
        entregable.setId(3);
        entregable.setTipo('E');
        entregable.setIdAsignatura(7);
        entregable.setDescripcion("hola");
        entregable.setFecha(new Timestamp(new GregorianCalendar(2014, 3, 24).getTimeInMillis()));

        var a = service.insertarEntregable(entregable);
        System.out.println(a);
    }

    @Test
    @Order(2)
    void consultarEntregablePorId() {
        var a = service.consultarPorIdEntregable(1);
        System.out.println(a);
    }

    @Test
    @Order(3)
    void actualizarEntregable() throws NoSeHaEncontradoException {

        Optional<Entregable> a = service.consultarPorIdEntregable(14);

        if(a.isPresent()){
            a.get().setDescripcion("adios");
            service.insertarEntregable(a.get());
            System.out.println(a);
        }else{
            throw new NoSeHaEncontradoException("No se ha encontrado el entregable");
        }

    }
    @Test
    @Order(4)
    void consultarEntregablePorIdModificado() {
        var a = service.consultarPorIdEntregable(1);
        System.out.println(a);
    }

    @Test
    @Order(5)
    void eliminarEntregable() throws NoSeHaEncontradoException {
        var a = service.consultarPorIdEntregable(7);

        if (a.isPresent()) {
            service.eliminarEntregable(a.get().getId());
            System.out.println("Eliminado");
        }

        else {
            throw new NoSeHaEncontradoException("El entregable buscado no existe");
        }

    }

    @Test
    @Order(6)
    void buscarTodoLosPagos(){

        System.out.println(service.consultarTodosEntregables());

    }

}
