package es.aulanosa.gestionfp.repository;


import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoExterno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlumnoExternoRepository extends JpaRepository<AlumnoExterno, Integer> {

    @Query("select a from AlumnoExterno a join fetch a.curso c where c.estado = 'A'")
    List<AlumnoExterno> findAllAlumnoExternosActivos();



}
