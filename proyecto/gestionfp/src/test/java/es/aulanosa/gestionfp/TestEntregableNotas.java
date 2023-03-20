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

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestEntregableNotas {

    @Autowired
    private EntregablesNotasServiceImp servicio;

    @Test
    @Order(1)
    public void testInsertarEntregableNotas(){
        EntregableNotas entregableNotas = new EntregableNotas();
        entregableNotas.setIdEntregable(50);
        entregableNotas.setIdAlumno(50);
        entregableNotas.setNota(9);
        var insertado = servicio.insertarEntregablesNotas(entregableNotas);
        System.out.println(insertado);

    }
}
