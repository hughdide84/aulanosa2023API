package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.Curso;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para Alumno
 */
public interface AlumnoService {

    //definicion de metodos CRUD para Alumno
    /**
     * Metodo para guardar un nuevo alumno
     * @param alumno Alumno a guardar
     * @return Alumno guardado
     */
    Alumno guardarAlumno(Alumno alumno);
    /**
     * Metodo para buscar todos los alumnos
     * @return Lista de todos los alumnos
     */
    List<Alumno> buscarTodo();
    /**
     * Metodo para buscar un alumno por su id
     * @param id id del alumno a buscar
     * @return Alumno encontrado con ese id
     */
    Optional<Alumno> buscarPorId(int id);
    /**
     * Metodo para modificar un alumno
     * @param alumno Alumno a modificar
     * @return Alumno modificado con los nuevos datos
     * @throws NoSeHaEncontradoException si no se encuentra el alumno
     */
    Alumno modificarAlumno(Alumno alumno) throws NoSeHaEncontradoException;
    /**
     * Metodo para eliminar un alumno por su id
     * @param id id del alumno a eliminar
     * @throws NoSeHaEncontradoException si no se encuentra el alumno
     */
    void eliminarAlumno(int id) throws NoSeHaEncontradoException;
    /**
     * Metodo para buscar un alumno por su nombre de usuario
     * @param nombre nombre del alumno a buscar
     * @return Alumno encontrado con ese nombre de usuario
     */
    Alumno buscarPorUsuario(String nombre);
    /**
     * Metodo para buscar un alumno por su estado del curso
     * @return Alumno encontrado con ese curso activo
     * @throws NoSeHaEncontradoException si no se encuentra el alumno
     */
    List<Alumno> buscarPorEstado() throws NoSeHaEncontradoException;
    /**
     * Metodo para buscar un alumno por su id de curso y id de estudios
     * @param idCurso
     * @param idEstudios
     * @return
     */
    List<Alumno> buscarPorCursoYEstudios(int idCurso, int idEstudios);
}
