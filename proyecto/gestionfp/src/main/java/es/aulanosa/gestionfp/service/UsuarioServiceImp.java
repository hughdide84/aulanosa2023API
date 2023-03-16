package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crea un nuevo usuario
    @Override
    @Transactional
    public Usuario crear(Usuario usuario) throws NoSeHaEncontradoException {
        Optional<Usuario> usuarioConsultado = usuarioRepository.findByNombre(usuario.getNombre());

        if (!usuarioConsultado.isPresent()) {
            return usuarioRepository.save(usuario);
        } else {
            throw new NoSeHaEncontradoException("Ya existe un usuario con ese nombre");
        }
    }

    // Lista el usuario cuyo id coincida con el introducido
    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> listarPorId(int id) {
        return usuarioRepository.findById(id);
    }

    // Lista el usuario cuyo nombre coincida con el introducido
    @Override
    public Optional<Usuario> consultarPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    // Lista los usuarios cuyo rol coincida con el introducido
    @Override
    public List<Usuario> consultarPorRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }

    // Lista todos los usuarios
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodo() {
        return usuarioRepository.findAll();
    }

    // Lista los usuarios que contengan en su nombre la cadena introducida
    @Override
    public List<Usuario> listarPorNombre(String cadenaNombre) {
        return usuarioRepository.findByNombreContains(cadenaNombre);
    }

    // Lista los usuarios que contengan en su email la cadena introducida
    @Override
    public List<Usuario> listarPorEmail(String cadenaEmail) {
        return usuarioRepository.findByEmailContains(cadenaEmail);
    }

    // Actualiza un usuario ya existente
    @Override
    @Transactional
    public Usuario actualizar(Usuario usuario) throws NoSeHaEncontradoException {
        Optional<Usuario> usuarioConsultado = usuarioRepository.findById(usuario.getId());

        if (usuarioConsultado.isPresent()) {
            Optional<Usuario> usuarioModificado = usuarioRepository.findByNombre(usuario.getNombre());
            if (!usuarioModificado.isPresent()) {
                return usuarioRepository.save(usuario);
            } else {
                throw new NoSeHaEncontradoException("Ya existe un usuario con ese nombre");
            }
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el usuario");
        }
    }

    // Borra el usuario cuyo id coincide con el introducido
    @Override
    @Transactional
    public void borrarPorId(Integer id) {
        usuarioRepository.deleteById(id);
    }
}