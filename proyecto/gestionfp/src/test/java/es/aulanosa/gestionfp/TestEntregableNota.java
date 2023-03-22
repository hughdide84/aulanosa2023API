package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.EntregableNota;
import es.aulanosa.gestionfp.service.EntregablesNotaServiceImp;
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
    private EntregablesNotaServiceImp servicio;

    @Test
    @Order(1)
    public void testInsertarEntregableNotas() throws NoSeHaEncontradoException {

        EntregableNota entregableNotas = new EntregableNota();
        entregableNotas.setIdEntregable(1);
        entregableNotas.setIdAlumno(4);
        entregableNotas.setNota(9);
        try {
            var a = servicio.insertarEntregablesNotas(entregableNotas);
            System.out.println(a);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No existe la entrega de notas", e);
        }
    }
    @Test
    @Order(2)
    public void testModificarEntregableNotas(){
        var consultado = servicio.buscarEntregablesNotasPorId(2);
        if (consultado.isPresent()){
            EntregableNota entregableNotas = consultado.get();
            entregableNotas.setNota(5);
            var modificado = servicio.modificarEntregablesNotas(entregableNotas);
            System.out.println(modificado);
        }
        else{
            System.out.println("Fallo al modificar Entregable Notas");
        }
    }
    @Test
    @Order(3)
    public void testConsultarEntregableNotasId(){
        var consultado = servicio.buscarEntregablesNotasPorId(2);
        if (consultado.isPresent()){
            EntregableNota entregableNotas = consultado.get();
            System.out.println(entregableNotas);
        }
        else{
            System.out.println("No se ha encontrado el Entregable Notas");
        }
    }
    @Test
    @Order(4)
    public void testEliminarEntregableNotas(){
        var consultado = servicio.buscarEntregablesNotasPorId(2);
        if (consultado.isPresent()){
            EntregableNota entregableNotas = consultado.get();
            servicio.eliminarEntregablesNotas(entregableNotas);
            System.out.println("Entregable Notas eliminado");
        }
        else{
            System.out.println("Entregable Notas no eliminado");
        }
    }
    @Test
    @Order(5)
    public void testConsultarTodosEntregableNotas(){
        var consultado = servicio.buscarEntregablesNotas();
        if (consultado.isEmpty()){
            System.out.println("Nos se ha encontrado Entregable Notas");
        }
        else{
            System.out.println("Entregable Notas consultado");
            for (EntregableNota entregableNotas : consultado) {
                System.out.println(entregableNotas);
            }
        }
    }
}





