package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AsignaturaHorario;
import es.aulanosa.gestionfp.service.AsignaturaHorarioService;
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
public class AsignaturaHorarioTextV1 {

    @Autowired
    AsignaturaHorarioServiceImp service;

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

        recuperado = service.insertar(asignaturaHorario);

        System.out.println(recuperado);

    }

    @Test
    @Order(2)
        //busca por la id
    void buscarPorIdAsignaturaHorario(){
        try{
            System.out.println(service.buscarPorId(2));
        }catch (NoSeHaEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
        //busca por la id de la asignatura
    void buscarPorIdAsignatura(){
        try{
            service.buscarPorCurso(recuperado.getIdAsignatura());
        }catch (NoSeHaEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    void actualizarAsignaturaHorario(){
        try{
            AsignaturaHorario asignaturaHorarioRecuperada = service.buscarPorId(recuperado.getId()).get();
            asignaturaHorarioRecuperada.setDia('z');
            service.modificar(asignaturaHorarioRecuperada);
        }catch (NoSeHaEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(2)
        //compruebo que se haya actualizado
    void comprobarActualizacion(){
        try{
            service.buscarPorId(recuperado.getId());
        }catch (NoSeHaEncontradoException e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(5)
    void eliminarAsignaturaHorario(){
        try{
            service.eliminar(1);

            if(!service.buscarPorId(1).isPresent()){
                System.out.println("La asignaturaHorario se ha borrado correctamente");
            }else{
                System.out.println("No se ha podido borrar la AsignaturaHorario");
            }
        }catch (NoSeHaEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(2)
        //busca por la id
    void listarTodoAsignaturaHorario(){
        List<AsignaturaHorario> lista = service.listarTodo();

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
