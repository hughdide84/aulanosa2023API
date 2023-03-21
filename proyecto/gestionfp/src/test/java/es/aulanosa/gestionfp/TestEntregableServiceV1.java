package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
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
import java.util.GregorianCalendar;

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

}
