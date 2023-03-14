package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Comentario;

import java.util.List;
import java.util.Optional;

public interface ComentarioService {
    Comentario save(Comentario comentario);

    Optional<Comentario> findById(Integer id);

    List<Comentario> findAll();

    List<Comentario> findBySistemaAndReferencia(char sistema, int referencia);

    Comentario update(Comentario comentario);

    void deleteById(Integer id);
}

