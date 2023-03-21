package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    /**
     * Lista el usuario cuyo id coincida con el introducido
     * @param id que sea desea consultar
     * @return Usuario usuario con ese id
     */
    Optional<Usuario> listarPorId(int id);

    /**
     * Crea un nuevo usuario
     * @param usuario con datos que queremos crear
     * @return Usuario usuario con id asignado
     * @throws NoSeHaEncontradoException si ya existe un usuario con ese nombre
     */
    Usuario crear(Usuario usuario) throws NoSeHaEncontradoException;

    /**
     * Actualiza un usuario ya existente
     * @param usuario con datos que queremos actualizar
     * @return Usuario usuario actualizado
     * @throws NoSeHaEncontradoException si no existe un usuario o ha habido un error
     */
    Usuario actualizar(Usuario usuario) throws NoSeHaEncontradoException;

    /**
     * Lista todos los usuarios
     * @return List<Usuario> lista de todos los usuarios
     */
    List<Usuario> listarTodo();

    /**
     * Borra el usuario cuyo id coincide con el introducido
     * @param id que sea desea borrar
     */
    void borrarPorId(Integer id);

    /**
     * Lista el usuario cuyo nombre coincida con el introducido
     * @param nombre que sea desea consultar
     * @return Usuario usuario con ese nombre
     */
    Optional<Usuario> consultarPorNombre(String nombre);

    /**
     * Lista los usuarios que contengan en su nombre la cadena introducida
     * @param cadenaNombre que sea desea consultar que contenga ese nombre
     * @return List<Usuario> lista de usuarios con ese nombre contenido
     */
    List<Usuario> listarPorNombre(String cadenaNombre);

    /**
     * Lista los usuarios que contengan en su email la cadena introducida
     * @param cadenaEmail que sea desea consultar que contenga ese email
     * @return List<Usuario> lista de usuarios con ese email contenido
     */
    List<Usuario> listarPorEmail(String cadenaEmail);

    /**
     * Lista los usuarios cuyo rol coincida con el introducido
     * @param rol que sea desea consultar
     * @return List<Usuario> lista de usuarios con ese rol
     */
    List<Usuario> consultarPorRol(String rol);
}