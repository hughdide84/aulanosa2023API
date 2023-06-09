package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Clase repository para las querys necesarias para la realización de las APIS, al extender de JPA los métodos de JPA se tienen en esta clase
 */
@Repository
public interface ProyectosRepository extends JpaRepository<Proyectos, Integer> {

    @Query("SELECT p FROM Proyectos p join fetch p.alumno a WHERE a.idCurso = ?1 and a.idEstudios = ?2")
    List<Proyectos> buscarPorCursoYEstudios(Integer idCurso, Integer idEstudios);
   @Query("Select p from Proyectos p join fetch p.alumnos a where a.idCurso = ?1 AND a.idEstudios = ?2 ")
   public List<Proyectos> buscarProyectosCursoyEstudios (int idCurso, int idEstudios);


}
