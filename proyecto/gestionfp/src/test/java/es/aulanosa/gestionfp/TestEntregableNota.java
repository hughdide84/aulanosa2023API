package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.EntregableNota;
import es.aulanosa.gestionfp.service.EntregableNotaServiceImp;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestEntregableNota {

    @Autowired
    private EntregableNotaServiceImp servicio;

    @Test
    @Order(1)
    public void testInsertarEntregableNota() throws NoSeHaEncontradoException {

        EntregableNota entregableNota = new EntregableNota();
        entregableNota.setIdEntregable(1);
        entregableNota.setIdAlumno(4);
        entregableNota.setNota(9);
        try {
            var a = servicio.insertarEntregableNota(entregableNota);
            System.out.println(a);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No se ha encontrado ese dato al insertar", e);
        }
    }

    @Test
    @Order(2)
    public void testConsultarEntregableNotaId() {
        var consultado = servicio.buscarEntregableNotaPorId(2);
        if (consultado.isPresent()) {
            EntregableNota entregableNota = consultado.get();
            System.out.println(entregableNota);
        } else {
            System.out.println("No se ha encontrado el Entregable Notas");
        }
    }

    @Test
    @Order(3)
    public void testModificarEntregableNota() {
        var consultado = servicio.buscarEntregableNotaPorId(3);
        if (consultado.isPresent()) {
            EntregableNota entregableNota = new EntregableNota();
            entregableNota.setId(3);
            entregableNota.setIdEntregable(1);
            entregableNota.setIdAlumno(4);
            entregableNota.setNota(10);
            try {
                var modificado = servicio.modificarEntregableNota(entregableNota);
                System.out.println(modificado);
            } catch (Exception e) {
                System.out.println("Fallo al modificar Entregable Notas");
            }
        } else {
            System.out.println("No existe el Entregable Notas");
        }
    }

    @Test
    @Order(4)
    public void testConsultarEntregableNotaId1() {
        var consultado = servicio.buscarEntregableNotaPorId(2);
        if (consultado.isPresent()) {
            EntregableNota entregableNota = consultado.get();
            System.out.println(entregableNota);
        } else {
            System.out.println("No se ha encontrado el Entregable Notas");
        }
    }

    @Test
    @Order(5)
    public void testEliminarEntregableNota() {
        var consultado = servicio.buscarEntregableNotaPorId(2);
        if (consultado.isPresent()) {
            EntregableNota entregableNota = consultado.get();
            servicio.eliminarEntregableNota(entregableNota);
            System.out.println("Entregable Notas eliminado");
        } else {
            System.out.println("Entregable Notas no eliminado");
        }
    }

    @Test
    @Order(6)
    public void testConsultarTodosEntregableNota() {
        var consultado = servicio.buscarEntregableNota();
        if (consultado.isEmpty()) {
            System.out.println("Nos se ha encontrado Entregable Notas");
        } else {
            System.out.println("Entregable Notas consultado");
            for (EntregableNota entregableNota : consultado) {
                System.out.println(entregableNota);
            }
        }
    }
}





