package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Comentario;

import java.util.List;
import java.util.Optional;

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
     * @param id 
     * @return
     */
    Optional<Comentario> listarPorId(Integer id);

    List<Comentario> listarTodo();

    List<Comentario> listarPorSistemaYReferencia(char sistema, int referencia);

    /**
     *
     * @param comentario
     * @return
     * @throws NoSeHaEncontradoException
     */
    Comentario actualizar(Comentario comentario) throws NoSeHaEncontradoException;

    void borrarPorId(Integer id);
}
