package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

    @Query("SELECT c FROM Curso c WHERE c.estado = 'A'")
    List<Curso> buscarTodoPorEstadoActivo();


}
