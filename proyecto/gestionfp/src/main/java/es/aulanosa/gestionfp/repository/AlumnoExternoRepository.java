package es.aulanosa.gestionfp.repository;


import es.aulanosa.gestionfp.model.AlumnoExterno;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que extiende de JpaRepository para la entidad AlumnoExterno
 */
public interface AlumnoExternoRepository extends JpaRepository<AlumnoExterno, Integer> {



}
