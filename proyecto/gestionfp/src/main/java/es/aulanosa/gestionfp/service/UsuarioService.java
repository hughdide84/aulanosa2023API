package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario findById(int id);
    List<Usuario> findAllByNombre(String nombre);
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    void deleteById(int id);
}
