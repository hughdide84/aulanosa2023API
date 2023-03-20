package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface AlumnoRepository extends JpaRepository <Alumno, Integer> {


    Alumno findByUsuario(String nombre);

    @Query("select a from Alumno a join fetch a.curso c where c.estado = 'A'")
    List<Alumno> findAllAlumnoActivos();

    @Query("select ae from AlumnoEmpresa ae join fetch alumno a join fetch empresa e where a.idCurso = ?1 and a.idEstudios = ?2 and ae.estado = 'C'")
    List<AlumnoEmpresa> buscarPorCursoEstudioEstado(int idCurso, int idEstudios);

}
