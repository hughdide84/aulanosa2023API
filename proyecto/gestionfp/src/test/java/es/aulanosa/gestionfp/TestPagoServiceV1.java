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
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestPagoServiceV1 {


    @Autowired
    private PagoServiceImp servicio;

    @Test
    @Order(1)
    public void insertarPago(){
        Pago pago = new Pago();
        pago.setIdMatricula(32);
        pago.setRecibo("A0003");
        pago.setEstado('a');
        pago.setObservaciones("adios");
        pago.setIdUsuario(14);
        pago.setFecha(new Timestamp(new GregorianCalendar(2014, 3, 24).getTimeInMillis()));
        var insertado = servicio.guardarPago(pago);
        System.out.println(insertado);

    }
    @Test
    @Order(2)
    void buscarPagoPorId() {
        System.out.println(servicio.buscarPorIdPago(32));
    }



    @Test
    @Order(3)
    void modificarPago() throws NoSeHaEncontradoException, NoSuchFieldException {
        Optional<Pago> optionalPago = servicio.buscarPorIdPago(33);



        if(optionalPago.isPresent()){
            optionalPago.get().setPago(238);
            Pago pago = servicio.modificarPago(optionalPago.get());

            System.out.println("Pago modificado a: " + optionalPago);
        }else{
            throw new NoSeHaEncontradoException("No se ha encontrado el pago especificado");
        }
    }

    @Test
    @Order(4)
    void buscarPagoPorIdModificado() {
        System.out.println(servicio.buscarPorIdPago(32));
    }
    @Test
    @Order(5)
    void eliminarPago(){

        var a = servicio.buscarPorIdPago(40);

        try{
            if(a.isPresent()){
                servicio.borrarPago(a.get().getId());
                System.out.println("Pago eliminado con exito");
            }else{
                System.out.println("No existe el id del pago especificado");
            }


        }catch (DataIntegrityViolationException e){
            System.out.println("No se puede eliminar porque existen relaciones con la entidad");
        }

    }
    @Test
    @Order(6)
    public void listarPorIdMatricula() throws NoSeHaEncontradoException {


        try {
            System.out.println(servicio.listarPorMatricula(35));
        }catch (NoSeHaEncontradoException e){
            System.out.println("No se a encontrado el idMatricula especificado");
        }



    }

    @Test
    @Order(7)
    void buscarTodoLosPagos(){

        System.out.println(servicio.buscarTodosPagos());

    }
}
