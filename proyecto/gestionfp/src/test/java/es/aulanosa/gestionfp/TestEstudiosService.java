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
            Estudios estudios = consultado.get();
            System.out.println(estudios);
        }
        else{
            System.out.println("Estudios no consultados");
        }
    }

    @Test
    @Order(3)
    public void testConsultarTodos(){
        var consultado = servicio.consultarTodos();
        if (consultado.isEmpty()){
            System.out.println("Estudios no consultados");
        }
        else{
            System.out.println("Estudios consultados");
        }
    }

    @Test
    @Order(4)
    public void testModificar() throws NoSuchFieldException {
        var consultado = servicio.consultarPorId(18);
        if (consultado.isPresent()){
            Estudios estudios = consultado.get();
            estudios.setNombre("Estudios de prueba modificados");
            var modificado = servicio.modificar(estudios);
            System.out.println(modificado);
        }
        else{
            System.out.println("Estudios no consultados");
        }
    }


    @Test
    @Order(5)
    public void testEliminar(){
        servicio.eliminar(17);
        if (servicio.consultarPorId(17).isPresent()){
            System.out.println("Estudios eliminados");
        }else {
            System.out.println("Estudios no encontrados/no eliminados");
        }
    }

}
