package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario listarPorId(int id);

    Usuario crear(Usuario usuario) throws NoSeHaEncontradoException;

    Usuario actualizar(Usuario usuario) throws NoSeHaEncontradoException;

    List<Usuario> listarTodo();

    void borrarPorId(int id);

    Optional<Usuario> consultarPorNombre(String nombre);

    List<Usuario> listarPorNombre(String cadenaNombre);

    List<Usuario> listarPorEmail(String cadenaEmail);

    List<Usuario> consultarPorRol(String rol);
}
