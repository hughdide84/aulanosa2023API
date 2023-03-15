package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.Alumno;  
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumnoEmpresaRepository extends JpaRepository<AlumnoEmpresa, Integer> {

    List<Alumno>
}
