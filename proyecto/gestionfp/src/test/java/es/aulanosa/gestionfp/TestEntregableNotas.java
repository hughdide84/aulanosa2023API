package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.model.EntregableNotas;
import es.aulanosa.gestionfp.service.EntregableNotasService;
import es.aulanosa.gestionfp.service.EntregablesNotasServiceImp;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestEntregableNotas {

    @Autowired
    private EntregablesNotasServiceImp servicio;

    @Test
    @Order(1)
    public void testInsertarEntregableNotas(){
        EntregableNotas entregableNotas = new EntregableNotas();
        entregableNotas.setIdEntregable(1);
        entregableNotas.setIdAlumno(4);
        entregableNotas.setNota(9);
        var insertado = servicio.insertarEntregablesNotas(entregableNotas);
        System.out.println(insertado);
    }
    @Test
    @Order(2)
    public void testConsultarEntregableNotasId(){
        var consultado = servicio.buscarEntregablesNotasPorId(2);
        if (consultado.isPresent()){
            EntregableNotas entregableNotas = consultado.get();
            System.out.println(entregableNotas);
        }
        else{
            System.out.println("Entregable Notas no consultado");
        }
    }
    @Test
    @Order(3)
    public void testModificarEntregableNotas(){
        var consultado = servicio.buscarEntregablesNotasPorId(2);
        if (consultado.isPresent()){
            EntregableNotas entregableNotas = consultado.get();
            entregableNotas.setNota(5);
            var modificado = servicio.modificarEntregablesNotas(entregableNotas);
            System.out.println(modificado);
        }
        else{
            System.out.println("Entregable Notas no consultado");
        }
    }
    @Test
    @Order(4)
    public void testEliminarEntregableNotas(){
        var consultado = servicio.buscarEntregablesNotasPorId(2);
        if (consultado.isPresent()){
            EntregableNotas entregableNotas = consultado.get();
            servicio.eliminarEntregablesNotas(entregableNotas);
            System.out.println("Entregable Notas eliminado");
        }
        else{
            System.out.println("Entregable Notas no consultado");
        }
    }
    @Test
    @Order(5)
    public void testConsultarTodosEntregableNotas(){
        var consultado = servicio.buscarEntregablesNotas();
        if (consultado.isEmpty()){
            System.out.println("Entregable Notas no consultado");
        }
        else{
            System.out.println("Entregable Notas consultado");
            for (EntregableNotas entregableNotas : consultado) {
                System.out.println(entregableNotas);
            }
        }
    }
}





