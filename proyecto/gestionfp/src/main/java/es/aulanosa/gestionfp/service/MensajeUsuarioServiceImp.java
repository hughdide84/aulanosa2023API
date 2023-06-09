package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Mensaje;
import es.aulanosa.gestionfp.model.MensajeUsuario;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.repository.MensajeUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Clase que implementa la interfaz MensajeUsuarioService
 */
@Service
public class MensajeUsuarioServiceImp implements MensajeUsuarioService {

    @Autowired
    MensajeUsuarioRepository repository;

    @Override
    @Transactional
    public MensajeUsuario insertar(MensajeUsuario mensajeUsuario) {
        return repository.save(mensajeUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MensajeUsuario> consultarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MensajeUsuario> consultarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public MensajeUsuario modificar(MensajeUsuario mensajeUsuario) throws NoSuchFieldException {
        if (repository.findById(mensajeUsuario.getId()).isPresent()){
            return repository.save(mensajeUsuario);
        }
        else{
            throw new NoSuchFieldException("El mensajeUsuario con id: " + mensajeUsuario.getId() + " no existe.");
        }
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mensaje> listarPorAutor(int usuarioId) {
        return repository.listarPorAutor(usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarPorDestinario(int mensajeId) {
        return repository.listarPorDestinatario(mensajeId);
    }
}
