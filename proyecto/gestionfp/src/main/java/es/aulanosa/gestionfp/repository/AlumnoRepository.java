package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/**
 * Interfaz que extiende de JpaRepository para poder utilizar sus métodos
 */
public interface AlumnoRepository extends JpaRepository <Alumno, Integer> {

    /**
     * Busca un alumno por su nombre de usuario
     * @param nombre nombre por el que se busca
     * @return Alumno encontrado
     */
    Alumno findByUsuario(String nombre);
    /**
     * Busca un alumno si el estado de su curso es activo
     * @return Alumno encontrado con curso activo
     */
    @Query("select a from Alumno a join fetch a.curso c where c.estado = 'A'")
    List<Alumno> findAllAlumnoActivos();
    /**
     * Busca un alumno por su id de curso y id de estudios
     * @param idCurso id del curso por el que se busca
     * @param idEstudios id de los estudios por el que se busca
     * @return Alumno encontrado con el curso y los estudios
     */
    List<Alumno> findByIdCursoAndIdEstudios(int idCurso, int idEstudios);
}
