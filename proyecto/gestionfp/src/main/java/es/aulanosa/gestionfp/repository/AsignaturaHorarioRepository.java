package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.AsignaturaHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio de asignaturas horario
 */
public interface AsignaturaHorarioRepository extends JpaRepository<AsignaturaHorario, Integer> {

    /**
     * Devuelve una lista de asignaturas horario por id de curso, id de estudio y nivel
     * @param idCurso por el que filtrar
     * @param idEstudio por el que filtrar
     * @param nivel por el que filtrar
     * @return List<AsignaturaHorario> lista de asignaturas horario
     */
    @Query("select a from AsignaturaHorario a join fetch a.asignatura asig where asig.curso.id = ?1 and asig.nivel = ?3 and asig.estudios.id = ?2")
    public List<AsignaturaHorario> findByIdAsignatura(int idCurso, int idEstudio, int nivel);



}