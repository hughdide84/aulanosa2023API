package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlumnoEmpresaRepository extends JpaRepository<AlumnoEmpresa, Integer> {

    @Query("select a from Alumno a join fetch a.alumnoEmpresa ae where ae.idEmpresa = ?1")
    List<Alumno> findAllAlumnoByEmpresaId(int empresaId);

    @Query("select e from Empresa e join fetch e.alumnoEmpresa ae where ae.idAlumno = ?1")
    List<Empresa> findAllEmpresaByAlumnoId(int alumnoId);

}
