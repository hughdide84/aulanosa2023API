package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.Empresa;
import es.aulanosa.gestionfp.service.EmpresaService;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestEmpresaV3 {

    @Autowired
    private EmpresaService empresaService;

    @Test
    @Order(1)
    void buscarEmpresaConAlumnosPorCursoEstudio() {
        var a = empresaService.buscarEmpresaConAlumnosPorCursoEstudio(3, 3);

        for (Map.Entry<Empresa, List<Alumno>> entry : a.entrySet()) {
            System.out.println(entry.getKey());
            for (Alumno alumno : entry.getValue()) {
                System.out.println(alumno);
            }
        }
    }
}
