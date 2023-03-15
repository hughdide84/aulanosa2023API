package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
<<<<<<< HEAD
    List<Comentario> findBySistemaAndReferencia(char sistema, int refencia);
=======
    List<Comentario> findBySistemaAndReferencia(char sistema, int referencia);
>>>>>>> Comentario-(Servicio,-repositorio-y-model)
}
