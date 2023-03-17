package es.aulanosa.gestionfp;


import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.Estudios;
import es.aulanosa.gestionfp.model.Pago;
import es.aulanosa.gestionfp.service.EstudiosServiceImp;
import es.aulanosa.gestionfp.service.PagoServiceImp;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Optional;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestPagoServiceV1 {


    @Autowired
    private PagoServiceImp servicio;

    @Test
    @Order(1)
    public void testInsertar(){
        Pago pago = new Pago();
        pago.setIdMatricula(31);
        pago.setRecibo("A0001");
        pago.setEstado('a');
        pago.setObservaciones("hola");
        pago.setIdUsuario(14);
        pago.setFecha(new Timestamp(new GregorianCalendar(2014, 3, 24).getTimeInMillis()));
        var insertado = servicio.guardar(pago);

    }
    @Test
    @Order(2)
    void consultarProyectoPorId() {
        System.out.println(servicio.buscarPorId(31));
    }

    @Test
    @Order(3)
    void buscarTodo(){

        System.out.println(servicio.buscarTodo());

    }

    @Test
    @Order(4)
    void modificarPago() throws NoSeHaEncontradoException, NoSuchFieldException {
        Optional<Pago> optionalPago = servicio.buscarPorId(31);



        if(optionalPago.isPresent()){
            optionalPago.get().setPago(238);
            Pago pago = servicio.modificar(optionalPago.get());

            System.out.println("Pago modificado a: " + optionalPago);
        }else{
            throw new NoSeHaEncontradoException("No se ha encontrado el pago especificado");
        }
    }

    @Test
    @Order(5)
    void eliminarAlumno(){

        try{
            servicio.borrar(31);
            System.out.println("Pago eliminado con exito");

        }catch (DataIntegrityViolationException e){
            System.out.println("No se puede eliminar porque existen relaciones con la entidad");
        }

    }
}
