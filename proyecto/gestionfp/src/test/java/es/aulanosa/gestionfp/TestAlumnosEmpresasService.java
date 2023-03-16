package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
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
public class TestAlumnosEmpresasService {

    @Autowired
    private AlumnosEmpresasService alumnosEmpresasService;

    @Test
    @Order(1)
    void insertarAlumnosEmpresas() {
        AlumnosEmpresas alumnosEmpresas = new AlumnosEmpresas();
        alumnosEmpresas.setIdAlumno(3);
        alumnosEmpresas.setIdEmpresa(7);
        alumnosEmpresas.setEstado('a');
        var a = alumnosEmpresasService.save(alumnosEmpresas);
        System.out.println(a);
    }

    @Test
    @Order(2)
    void consultarAlumnosEmpresasPorId() {
        var a = alumnosEmpresasService.findById(1);
        System.out.println(a);
    }

    @Test
    @Order(3)
    void updateAlumnosEmpresas() throws NoSeHaEncontradoException {
        var b = alumnosEmpresasService.findById(1);
        if (b != null) {
            b.setEstado('b');
            var a = alumnosEmpresasService.save(b);
            System.out.println(a);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el registro");
        }
    }

    @Test
    @Order(4)
    void consultarTodos() {
        var a = alumnosEmpresasService.findAll();
        System.out.println(a);
    }

    @Test
    @Order(5)
    void deleteAlumnosEmpresas() throws NoSeHaEncontradoException {
        var b = alumnosEmpresasService.findById(1);
        if (b != null) {
            alumnosEmpresasService.deleteById(b.getId());
            System.out.println("Registro eliminado");
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el registro");
        }
    }

}
