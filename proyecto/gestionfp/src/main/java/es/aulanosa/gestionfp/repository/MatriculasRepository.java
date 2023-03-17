package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Matriculas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculasRepository extends JpaRepository<Matriculas,Integer> {

    public List<Matriculas> buscarPorNombre (String nombre);
}
