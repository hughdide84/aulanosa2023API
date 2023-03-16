package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.service.AlumnoEmpresaService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestAlumnoEmpresaService {

    @Autowired
    private AlumnoEmpresaService alumnoEmpresaService;

    @Test
    @Order(1)
    void insertarAlumnosEmpresas() throws NoSeHaEncontradoException {
        AlumnoEmpresa alumnosEmpresas = new AlumnoEmpresa();
        alumnosEmpresas.setIdAlumno(3);
        alumnosEmpresas.setIdEmpresa(8);
        alumnosEmpresas.setEstado('a');
        try {
            var a = alumnoEmpresaService.guardar(alumnosEmpresas);
            System.out.println(a);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No existe el alumno o la empresa", e);
        }
    }

    @Test
    @Order(2)
    void consultarAlumnosEmpresasPorId() {
        var a = alumnoEmpresaService.buscarPorId(1);
        System.out.println(a);
    }

    @Test
    @Order(3)
    void modificarAlumnosEmpresas() throws NoSeHaEncontradoException {
        var b = alumnoEmpresaService.buscarPorId(1);
        if (b != null) {
            b.setEstado('b');
            var a = alumnoEmpresaService.modificar(b);
            System.out.println(a);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el registro");
        }
    }

    @Test
    @Order(4)
    void consultarTodos() {
        var a = alumnoEmpresaService.buscarTodo();
        System.out.println(a);
    }

    @Test
    @Order(5)
    void borrarAlumnosEmpresas() throws NoSeHaEncontradoException {
        var b = alumnoEmpresaService.buscarPorId(1);
        if (b != null) {
            alumnoEmpresaService.borrarPorId(b.getId());
            System.out.println("Registro eliminado");
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el registro");
        }
    }

    @Test
    @Order(6)
    void consultarAlumnosPorIdEmpresa() {
        var a = alumnoEmpresaService.buscarTodosAlumnosPorIdEmpresa(7);
        System.out.println(a);
    }

    @Test
    @Order(7)
    void consultarEmpresasPorIdAlumno() {
        var a = alumnoEmpresaService.buscarTodasEmpresasPorIdAlumno(3);
        System.out.println(a);
    }

}
