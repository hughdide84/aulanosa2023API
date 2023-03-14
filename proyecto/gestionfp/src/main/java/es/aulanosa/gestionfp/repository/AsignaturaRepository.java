package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {

    List<Asignatura> findAllByNombre(String nombre);
}
