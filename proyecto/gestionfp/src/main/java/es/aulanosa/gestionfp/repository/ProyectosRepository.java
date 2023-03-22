package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyectos, Integer> {

    @Query("SELECT p FROM Proyectos p join fetch p.alumno a WHERE a.idCurso = ?1 and a.idEstudios = ?2")
    List<Proyectos> buscarPorCursoYEstudios(Integer idCurso, Integer idEstudios);

}
