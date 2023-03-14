package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    @Query(value = "SELECT * FROM Comentarios "
            + "WHERE lower(sistema) = :sistema "
            + "AND lower(referencia) = :referencia", nativeQuery = true)
    List<Comentario> listarPorSistemaYReferencia(@Param("sistema") char sistema, @Param("referencia") int referencia);
}
