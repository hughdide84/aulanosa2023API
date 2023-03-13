package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    //busca todos los usuarios
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    //busca todos los usuarios por nombre
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllByNombre(String nombre) {
        return usuarioRepository.findAllByNombre(nombre);
    }

    //busca usuario por id
    @Override
    @Transactional(readOnly = true)
    public Usuario findById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    //guarda un usuario
    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //borra un usuario por id
    @Override
    @Transactional
    public void deleteById(int id) {
        usuarioRepository.deleteById(id);
    }
}
