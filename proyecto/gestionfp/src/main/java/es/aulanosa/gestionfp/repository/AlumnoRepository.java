package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlumnoRepository extends JpaRepository <Alumno, Integer> {

    @Query("select a from Alumno a where a.estado = ?1")
    List<Alumno> findAllAlumnoActivos(String estado);

}
