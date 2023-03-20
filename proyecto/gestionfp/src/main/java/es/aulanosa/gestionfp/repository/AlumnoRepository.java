package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository <Alumno, Integer> {

    Alumno findByUsuario(String nombre);
}
