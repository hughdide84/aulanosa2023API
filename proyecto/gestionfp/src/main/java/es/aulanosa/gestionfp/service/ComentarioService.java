package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Comentario;

import java.util.List;
import java.util.Optional;

public interface ComentarioService {
    Comentario crear(Comentario comentario);

    Optional<Comentario> listarPorId(Integer id);

    List<Comentario> listarTodo();

    List<Comentario> listarPorSistemaYReferencia(char sistema, int referencia);

    Comentario actualizar(Comentario comentario);

    void borrarPorId(Integer id);
}
