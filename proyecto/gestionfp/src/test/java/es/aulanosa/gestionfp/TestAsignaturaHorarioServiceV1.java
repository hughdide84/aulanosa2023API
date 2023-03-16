package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AsignaturaHorario;
import es.aulanosa.gestionfp.service.AsignaturaHorarioServiceImp;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.List;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestAsignaturaHorarioServiceV1 {

    @Autowired
    AsignaturaHorarioServiceImp service;


    //en asignatura hay id 7, 8 y 9
    int idAsignaturaHorarioGeneral = 2;

    //en asignaturaHorario hay id 2 y 4
    int idAsignatura = 7;
    AsignaturaHorario recuperado = new AsignaturaHorario();


    @Test
    @Order(1)
    void insertarAsignaturaHorario(){
        AsignaturaHorario asignaturaHorario = new AsignaturaHorario();

        asignaturaHorario.setDia('a');
        asignaturaHorario.setIdAsignatura(7);
        asignaturaHorario.setFin(new Time(1, 1, 1));
        asignaturaHorario.setInicio(new Time(1,1,1));
        asignaturaHorario.setDia('a');

        recuperado = service.insertarAsignaturaHorario(asignaturaHorario);

        System.out.println(recuperado);

    }

    @Test
    @Order(2)
        //busca por la id
    void buscarPorIdAsignaturaHorario(){
        try{
            System.out.println(service.buscarPorIdAsignaturaHorario(idAsignatura));
        }catch (NoSeHaEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
        //busca por la id de la asignatura
    void buscarPorIdAsignatura(){
        try{
            List<AsignaturaHorario> lista = service.buscarPorCursoAsignaturaHorario(5, 3, 1);
            for (AsignaturaHorario asig :
                    lista) {
                System.out.println(asig);
            }
        }catch (NoSeHaEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    void actualizarAsignaturaHorario(){
        try{
            AsignaturaHorario asignaturaHorarioRecuperada = service.buscarPorIdAsignaturaHorario(5).get();
            asignaturaHorarioRecuperada.setDia('z');
            service.modificarAsignaturaHorario(asignaturaHorarioRecuperada);
            System.out.println("AsignaturaHorario modificada correctamente");
        }catch (NoSeHaEncontradoException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    @Order(5)
        //compruebo que se haya actualizado
    void comprobarActualizacion(){
        try{
            recuperado = service.buscarPorIdAsignaturaHorario(idAsignatura).get();
            System.out.println(recuperado);
        }catch (NoSeHaEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    void eliminarAsignaturaHorario(){
        try{
            service.eliminarAsignaturaHorario(7);
            System.out.println("Se ha borrado correctamente el ID proporcionado");
        }catch (NoSeHaEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
        //busca por la id
    void listarTodoAsignaturaHorario(){
        List<AsignaturaHorario> lista = service.listarTodoAsignaturaHorario();

        if(lista.isEmpty()){
            System.out.println("La base de datos no tiene campos que mostrar");
        }else{
            for (AsignaturaHorario asignaturaHorario :
                    lista) {
                System.out.println(asignaturaHorario);
            }
        }
    }


}
