package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Curso;

import java.util.List;

/**
 * Definicion de metodos CRUD mas uno para buscar todos los cursos con estado activo.
 * Los siguientes metodos son implementados en la clase de CursoServiceImp que implementa esta interfaz
 */

//definir metodos a implementar
public interface CursoService {

    /**
     * Metodo para insertar un curso en la BD
     * @param curso se le pasa como parametro un objeto curso
     * @return devuelve un objeto del curso insertado
     */
    Curso insertarCurso(Curso curso);

    /**
     * Metodo para buscar todos los cursos existentes en la BD
     * @return devuelve una lista de cursos
     */
    List<Curso> buscarTodo();

    /**
     * Metodo para buscar un curso a traves de un id existente en la BD
     * @param id se le pasa el id del curso que se quiere buscar
     * @return devuelve un objeto curso
     */
     Curso buscarPorId(int id);

    /**
     * Metdodo para modificar un curso existente en la BD
     * @param curso se le pasa como parametro un objeto curso para que a traves de su id lo recupere de la BD y cambiar los valores necesarios
     * @return devuelve un objeto curso del que ha sido modificado
     * @throws NoSeHaEncontradoException en el caso de que no se encuentre el curso a modificar saltaria esta excepcion
     */
    Curso modificarCurso(Curso curso) throws NoSeHaEncontradoException;

    /**
     * Metodo para eliminar un curso existente en la BD a traves de su id
     * @param id se le pasa el id como parametro para encontrar el curso
     * @throws NoSeHaEncontradoException en el caso del que id no coincida con ninguno de la BD saltaria esta excepcion
     */
     void eliminarCurso(int id) throws NoSeHaEncontradoException;

    /**
     * Metodo para listar todos los cursos que en el campo estado tienen el valor activo
     * @return devuelve una lista de todos los cursos activos
     */

     List<Curso> buscarTodoPorEstadoActivo();







}
