package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculasRepository extends JpaRepository <Matricula,Integer> {

    @Query("SELECT m FROM Matricula m WHERE m.nombre = ?1")
    public List<Matricula> buscarPorNombre (String nombre);

    @Query("SELECT c FROM Curso c join fetch c.matriculas cm WHERE cm.idCurso = ?1")
    public List<Curso> buscarTodosCursosPorId (Integer id);
}
