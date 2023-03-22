package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Comentario;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz con todos los métodos necesarios para la realización del Service
 */
public interface ComentarioService {
    /**
     *  Inserta un nuevo comentario en la base de datos, debe ser un objeto con los métodos necesarios
     *  dentro del mismo, es decir, si el campo "sistema" no puede ser nulo, debe de estar completo
     * @param comentario Objeto que se le pasa para crear una nueva inserción
     * @return Devuelve el objeto comentario para enseñar al usuario lo que acaba de insertar en la BD
     */
    Comentario crear(Comentario comentario);

    /**
     * Lista el campo correspondiente al id proporcionado
     * @param id Dato representativo de cada campo de la BD
     * @return Devuelve o no un objeto Comentario
     */
    Optional<Comentario> listarPorId(Integer id);

    /**
     * Lista todos los campos de la BD
     * @return Devuelve una lista de Comentarios
     */
    List<Comentario> listarTodo();

    /**
     * Lista los campos de la BD que tengan el sistema y referencia especificados en los parámetros
     * @param sistema Campo Sistema de la tabla
     * @param referencia Campo Referencia de la tabla
     * @return Devuelve una lista de objetos Comentario que contienen los datos de la BD
     */

    List<Comentario> listarPorSistemaYReferencia(char sistema, int referencia);

    /**
     * Actualiza el campo correspondiente al objeto proporcionado como parametro
     * @param comentario Objeto que representa un campo de la base de datos
     * @return Devuelve un objeto Comentario actualizado
     * @throws NoSeHaEncontradoException En caso de que el objeto Comentario no conste en la BD, devuelve este error
     */
    Comentario actualizar(Comentario comentario) throws NoSeHaEncontradoException;

    /**
     * Borra un campo con el id especificado en el parámetro
     * @param id Dato representativo en la BD
     */
    void borrarPorId(Integer id);

    /**
     * Lista los registros que contengan el sistema e Id proporcionados
     * @param sistema variable char para representar el campo Sistema de la BD
     * @param id variable int para representar el campo ID de la BD
     * @return Devuelve una lista de comentarios para mostrar todos los registros que coincidan
     */
    List<Comentario> listarPorSistemaEId(char sistema, int id) throws NoSeHaEncontradoException;
}
