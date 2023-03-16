package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    // Lista el usuario cuyo id coincida con el introducido
    Usuario listarPorId(int id);

    // Crea un nuevo usuario
    Usuario crear(Usuario usuario) throws NoSeHaEncontradoException;

    // Actualiza un comentario ya existente
    Usuario actualizar(Usuario usuario) throws NoSeHaEncontradoException;

    // Lista todos los usuarios
    List<Usuario> listarTodo();

    // Borra el usuario cuyo id coincide con el introducido
    void borrarPorId(Integer id);

    // Lista el usuario cuyo nombre coincida con el introducido
    Optional<Usuario> consultarPorNombre(String nombre);

    // Lista los usuarios que contengan en su nombre la cadena introducida
    List<Usuario> listarPorNombre(String cadenaNombre);

    // Lista los usuarios que contengan en su email la cadena introducida
    List<Usuario> listarPorEmail(String cadenaEmail);

    // Lista los usuarios cuyo rol coincida con el introducido
    List<Usuario> consultarPorRol(String rol);
}