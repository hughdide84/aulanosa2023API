package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.model.Estudios;
import es.aulanosa.gestionfp.service.EstudiosService;
import es.aulanosa.gestionfp.service.EstudiosServiceImp;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEstudiosService {

    @Autowired
    private EstudiosServiceImp servicio;

    @Test
    @Order(1)
    public void testInsertar(){
       Estudios estudios = new Estudios();
       estudios.setNombre("Estudios de prueba");
       estudios.setFct(true);
       estudios.setPext(true);
       var insertado = servicio.insertar(estudios);
       System.out.println(insertado);
    }

    @Test
    @Order(2)
    public void testConsultar(){
        var consultado = servicio.consultarPorId(18);
        if (consultado.isPresent()){
            System.out.println("Estudios consultados");
        }
        else{
            System.out.println("Estudios no consultados");
        }
    }

    @Test
    @Order(3)
    public void testEliminar(){
        servicio.eliminar(1);
        if (servicio.consultarPorId(1).isEmpty()){
            System.out.println("Estudios eliminados");
        }
        else{
            System.out.println("Estudios no eliminados");
        }
    }

}
