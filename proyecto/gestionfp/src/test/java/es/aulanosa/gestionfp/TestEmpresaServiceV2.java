package es.aulanosa.gestionfp;

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
    void insertarEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setNombre("Empresa 1");
        empresa.setIdCurso(1);
        empresa.setIdEstudios(1);
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
        empresaService.save(empresa);

    }

    @Test
    void consultarEmpresas() {
        var a = empresaService.findAll();
        System.out.println(a);
    }

}
