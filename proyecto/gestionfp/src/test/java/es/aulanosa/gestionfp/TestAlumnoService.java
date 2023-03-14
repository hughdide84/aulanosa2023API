package es.aulanosa.gestionfp;


import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.AlumnoService;
import es.aulanosa.gestionfp.service.UsuarioService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestAlumnoService {

    @Autowired
    private AlumnoService service;

    @Test()
    @Order(1)
    void insertarUsuario() {
        Alumno alumno = new Alumno();
        alumno.setIdCurso(100);
        alumno.setIdEstudios(100);
        alumno.setNombre("alumnoNuevo");
        alumno.setCv('a');
        alumno.setCarta('b');
        alumno.setIdEmpresa(100);
        alumno.setInicioPr();
        var a = service.guardar(alumno);
        System.out.println(a);

    }

}
