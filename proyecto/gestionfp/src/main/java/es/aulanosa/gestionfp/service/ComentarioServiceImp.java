package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Comentario;
import es.aulanosa.gestionfp.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class ComentarioServiceImp implements ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;

    // Guarda un comentario
    @Override
    @Transactional
    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Lista un comentario por ID
    @Override
    @Transactional(readOnly = true)
    public Optional<Comentario> findById(Integer id) {
        return comentarioRepository.findById(id);
    }

    // Lista todos los comentarios
    @Override
    @Transactional(readOnly = true)
    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    // Lista todos los comentarios con un sistema y referencia concretos
    @Override
    @Transactional(readOnly = true)
    public List<Comentario> findBySistemaAndReferencia(char sistema, int referencia) {
        return comentarioRepository.listarPorSistemaYReferencia(sistema, referencia);
    }

    // Actualiza un comentario
    @Override
    @Transactional
    public Comentario update(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Borra un comentario por ID
    @Override
    @Transactional
    public void deleteById(Integer id) {
        comentarioRepository.deleteById(id);
    }
}
