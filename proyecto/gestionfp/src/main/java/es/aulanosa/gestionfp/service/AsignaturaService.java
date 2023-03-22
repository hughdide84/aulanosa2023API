package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Asignatura;

import java.util.List;

/**
 * Esta interfaz define los métodos que se pueden utilizar para interactuar con la entidad de asignatura.
 */

public interface AsignaturaService {

    /**
     * Busca todas las asignaturas en la base de datos.
     * @return Una lista de todas las asignaturas.
     */
    List<Asignatura> buscarTodoAsignatura();
    /**
     * Busca todas las asignaturas en la base de datos que contienen el nombre de la asignatura dado.
     * @param nombre El nombre de la asignatura a buscar.
     * @return Una lista de todas las asignaturas que contienen el nombre de la asignatura dado.
     */
    List<Asignatura> buscarTodoPorNombreAsignaturaConteniendoNombreAsignatura(String nombre);
    /**
     * Busca una asignatura por su identificador en la base de datos.
     * @param id El identificador de la asignatura a buscar.
     * @return La asignatura correspondiente al identificador dado, o null si no se encuentra.
     */
    Asignatura buscarPorIdAsignatura(int id);
    /**
     * Guarda una asignatura en la base de datos.
     * @param asignatura La asignatura a guardar.
     * @return La asignatura guardada con su identificador actualizado.
     */
    Asignatura guardarAsignatura(Asignatura asignatura);
    /**
     * Borra una asignatura de la base de datos por su identificador.
     * @param id El identificador de la asignatura a borrar.
     */
    void borrarPorIdAsignatura(int id);
    /**
     * Modifica una asignatura en la base de datos.
     * @param asignatura La asignatura modificada.
     * @return La asignatura modificada.
     */
    Asignatura modificarAsignatura(Asignatura asignatura);

}
