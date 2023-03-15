package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Comentario;
import es.aulanosa.gestionfp.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServiceImp implements ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;

    // Guarda un comentario
    @Override
    @Transactional
    public Comentario crear(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Lista un comentario por ID
    @Override
    @Transactional(readOnly = true)
    public Optional<Comentario> listarPorId(Integer id) {
        return comentarioRepository.findById(id);
    }

    // Lista todos los comentarios
    @Override
    @Transactional(readOnly = true)
    public List<Comentario> listarTodo() {
        return comentarioRepository.findAll();
    }

    // Lista todos los comentarios con un sistema y referencia concretos
    @Override
    @Transactional(readOnly = true)
    public List<Comentario> listarPorSistemaYReferencia(char sistema, int referencia) {
        return comentarioRepository.findBySistemaAndReferencia(sistema, referencia);
    }

    // Actualiza un comentario
    @Override
    @Transactional
    public Comentario actualizar(Comentario comentario) {
        Optional<Comentario> comentarioConsultado = comentarioRepository.findById(comentario.getId());

        if (comentarioConsultado.isPresent()) {
            return comentarioRepository.save(comentario);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el comentario");
        }
    }

    // Borra un comentario por ID
    @Override
    @Transactional
    public void borrarPorId(Integer id) {
        comentarioRepository.deleteById(id);
    }
}
