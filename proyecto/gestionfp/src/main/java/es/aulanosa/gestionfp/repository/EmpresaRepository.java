package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    List<Empresa> findAllByNombre(String nombre);

    @Query("select e from Empresa e where e.idCurso = ?1 and e.idEstudios = ?2")
    List<Empresa> buscarPorIdCursoYEstudios(int idCurso, int idEstudios);
}
