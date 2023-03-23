package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio que extiende del JpaRepository lo que ya hace operaciones predefinidas contra la BD, si fuera necesario puede hacerse una query manualmente
 */
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    /**
     *La siguiente query busca todos los cursos cuyo estado sea activo "A"
     * @return devuleve una lista de cursos con estado activo
     */
    @Query("SELECT c FROM Curso c WHERE c.estado = 'A'")
    List<Curso> buscarTodoPorEstadoActivo();


}
