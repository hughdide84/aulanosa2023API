package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.dto.CursoEstudioNivelDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AsignaturaHorario;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los métodos de la capa de servicio de AsignaturaHorario
 */
public interface AsignaturaHorarioService {

    /**
     * Busca un AsignaturaHorario por su id
     * @param id del AsignaturaHorario
     * @return AsignaturaHorario con el id introducido
     * @throws NoSeHaEncontradoException si no se encuentra el AsignaturaHorario
     */
    Optional<AsignaturaHorario> buscarPorIdAsignaturaHorario(Integer id) throws NoSeHaEncontradoException;

    /**
     * Inserta un AsignaturaHorario en la base de datos
     * @param asignaturaHorario a insertar
     * @return AsignaturaHorario insertado
     */
    AsignaturaHorario insertarAsignaturaHorario(AsignaturaHorario asignaturaHorario);

    /**
     * Modifica un AsignaturaHorario
     * @param asignaturaHorario a modificar
     * @return AsignaturaHorario modificado
     * @throws NoSeHaEncontradoException si no se encuentra el AsignaturaHorario
     */
    AsignaturaHorario modificarAsignaturaHorario(AsignaturaHorario asignaturaHorario) throws NoSeHaEncontradoException;

    /**
     * 
     * @param idAsignatura
     * @param idCurso
     * @param nivel
     * @return
     * @throws NoSeHaEncontradoException
     */
    List<AsignaturaHorario> buscarPorCursoAsignaturaHorario(int idAsignatura, int idCurso, int nivel) throws NoSeHaEncontradoException;
    /**
     * Elimina un AsignaturaHorario de la base de datos
     * @param id del AsignaturaHorario a eliminar
     * @throws NoSeHaEncontradoException si no se encuentra el AsignaturaHorario
     */
    void eliminarAsignaturaHorario(int id) throws NoSeHaEncontradoException;

    List<AsignaturaHorario> listarTodoAsignaturaHorario();

    List<AsignaturaHorario> listarHorariosSegunCursoEstudioNivel(CursoEstudioNivelDTO cursoEstudioNivelDTO) throws NoSeHaEncontradoException;

}