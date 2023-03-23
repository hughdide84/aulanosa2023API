package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
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

}