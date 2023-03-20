package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    List<Empresa> findAllByNombre(String nombre);

    @Query("select e from Empresa e join fetch e.alumnoEmpresa ae join fetch ae.alumno a join fetch ae.empresa em where a.idCurso = ?1 and a.idEstudios = ?2 and ae.estado = 'C'")
    List<Empresa> buscarEmpresaConAlumnosPorCursoEstudio(int idCurso, int idEstudios);
}
