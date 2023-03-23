package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Empresa;
import es.aulanosa.gestionfp.service.EmpresaService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestEmpresaServiceV2 {

    @Autowired
    private EmpresaService empresaService;

    @Test
    @Order(1)
    void insertarEmpresa() throws NoSeHaEncontradoException {
        Empresa empresa = new Empresa();
        empresa.setNombre("Empresa 1");
        empresa.setIdCurso(3);
        empresa.setIdEstudios(3);
        empresa.setCif("A12345678");
        empresa.setContacto("Juan");
        empresa.setConvenio('a');
        empresa.setRepresentante("Juan");
        empresa.setDireccionSocial("Calle 1");
        empresa.setDireccionTrabajo("Calle 1");
        empresa.setTutor1("Juan");
        empresa.setTutor2("Juan");
        empresa.setTutor3("Juan");
        empresa.setPlanIndividual('a');
        empresa.setHojaActividades('a');
        try {
            var insertado = empresaService.save(empresa);
            System.out.println(insertado);
        } catch (Exception e) {
            throw new NoSeHaEncontradoException("No existe la empresa", e);
        }
    }

    @Test
    @Order(2)
    void consultarPorId() {
        var a = empresaService.findById(4);
        System.out.println(a);
    }

    @Test
    @Order(3)
    void modificar() throws NoSeHaEncontradoException {

        var a = empresaService.findById(4);
        if (a != null) {
            a.setNombre("Empresa 22");
            var b = empresaService.save(a);
            System.out.println(b);
        } else {
            throw new NoSeHaEncontradoException("No existe la empresa");
        }
    }

    @Test
    @Order(4)
    void consultarEmpresas() {
        var a = empresaService.findAll();
        System.out.println(a);
    }

    @Test
    @Order(5)
    void borrar () throws NoSeHaEncontradoException {

        var a = empresaService.findById(6);
        if (a != null) {
            empresaService.deleteById(a.getId());
            System.out.println("Borrado");
        } else {
            throw new NoSeHaEncontradoException("No existe la empresa");
        }
    }

    @Test
    @Order(6)
    void consultarEmpresaPorNombre() {
        var a = empresaService.findAllByNombre("Empresa 22");
        System.out.println(a);
    }

    @Test
    @Order(7)
    void consultarEmpresaPorCursoYEstudios() {
        var a = empresaService.findByIdCursoAndIdEstudio(3, 3);
        System.out.println(a);
    }

}
