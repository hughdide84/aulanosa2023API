package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.model.AlumnosEmpresas;
import es.aulanosa.gestionfp.service.AlumnosEmpresasService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestUsuariosEmpresasService {

    @Autowired
    private AlumnosEmpresasService alumnosEmpresasService;

    @Test
    @Order(1)
    void insertarAlumnosEmpresas() {
        AlumnosEmpresas alumnosEmpresas = new AlumnosEmpresas();
        alumnosEmpresas.setIdAlumno(1);
        alumnosEmpresas.setIdEmpresa(1);
        alumnosEmpresas.setEstado('a');
        var a = alumnosEmpresasService.save(alumnosEmpresas);
        System.out.println(a);
    }

}
