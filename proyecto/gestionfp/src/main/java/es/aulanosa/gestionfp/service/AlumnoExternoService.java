package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoExterno;

import java.util.List;
import java.util.Optional;

public interface AlumnoExternoService {
    /**
     * Método que inserta un alumnoExterno en la base de datos
     * @return un nuevo alumnoExterno
     */
    List<AlumnoExterno> listarTodo();

    /**
     * Método que elimina un alumnoExterno
     * @param id
     * return el alumnoExterno eliminado
     */
    public void eliminar(Integer id) throws NoSeHaEncontradoException;
    /**
     * Método que lista los alumnoExterno que ha enviado un usuario
     * @param alumnoExterno
     * @return una lista con los alumnoExterno de un usuario
     */
    public AlumnoExterno guardar(AlumnoExterno alumnoExterno);
    /**
     * Método que modifica un alumnoExterno
     * @param alumnoExterno
     * @return el alumnoExterno especifico que queramos modificado
     * @throws NoSuchFieldException si el alumnoExterno no existe
     */
    public AlumnoExterno modificar(AlumnoExterno alumnoExterno) throws NoSeHaEncontradoException;

    /**
     * Método que lista los alumnoExterno que ha enviado un usuario
     * @param id
     * @return una lista con los alumnoExterno de un usuario
     */
    public Optional<AlumnoExterno> listarPorId(Integer id) throws NoSeHaEncontradoException;
    List<AlumnoExterno> buscarPorEstado() throws NoSeHaEncontradoException;
    List<AlumnoExterno> buscarPorNombreConteniendo(String nombre);
    AlumnoExterno buscarPorNombreEs(String nombre);
    List<AlumnoExterno> buscarPorEmailConteniendo(String email);
    AlumnoExterno buscarPorEmailEs(String email);
    AlumnoExterno buscarPorTelefono(String telefono);
    List<AlumnoExterno> buscarPorUniversidadEs(String universidad);
    List<AlumnoExterno> buscarPorUniversidadConteniendo(String universidad);
    List<AlumnoExterno> buscarPorTitulacionConteniendo(String titulacion);
    List<AlumnoExterno> buscarPorTitulacionEs(String titulacion);
    List<AlumnoExterno> buscarPorEspecialidadConteniendo(String especialidad);
    List<AlumnoExterno> buscarPorEspecialidadEs(String especialidad);

    /**
     * Método para listar todos los AlumnosExternos de la BD relacionados con un IdCurso proporcionado
     * @param id IdCurso para la busqueda de datos
     * @return Lista con todos los AlumnosExternos que se relacionen con ese curso
     */
    List<AlumnoExterno> buscarPorIdCurso(Integer id) throws NoSeHaEncontradoException;

}