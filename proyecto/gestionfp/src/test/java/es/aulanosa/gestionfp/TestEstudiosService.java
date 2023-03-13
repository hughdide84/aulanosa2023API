package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.model.Estudios;
import es.aulanosa.gestionfp.service.EstudiosServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEstudiosService {

    @Autowired
    private EstudiosServiceImp servicio;

    @Test
    public void testConsultarPorId(){
        servicio.consultarPorId(1);
    }

    @Test
    public void testBorrar(){
        Estudios estudios = new Estudios();
        servicio.borrar(estudios);
    }

    @Test
    public void testInsertar(){
       Estudios estudios = new Estudios(0,"descripcion", true, false);
       servicio.insertar(estudios);
    }

}
