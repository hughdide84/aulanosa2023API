package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    List<Empresa> findAllByNombre(String nombre);
    List<Empresa> findByIdCurso(int idCurso);
}
