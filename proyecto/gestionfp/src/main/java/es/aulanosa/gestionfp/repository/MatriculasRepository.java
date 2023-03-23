package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase que implementa la interfaz JpaRepository para la entidad Matricula
 */
@Repository
public interface MatriculasRepository extends JpaRepository<Matricula,Integer> {
    /**
     * Método que devuelve una lista de matriculas a partir de un nombre especificado
     * @param nombre
     * @return lista de matriculas con ese nombre
     */
    @Query("SELECT m FROM Matricula m WHERE m.nombre = ?1")
    public List<Matricula> buscarPorNombre (String nombre);

    /**
     * Método que devuelve una lista de matriculas a partir de un mes especificado
     * @param mes
     * @return lista de matriculas con ese mes
     */
    @Query("SELECT m FROM Matricula m WHERE month(fecha) = ?1")
    public List<Matricula> buscarPorMes (Integer mes);

    /**
     * Método que devuelve una lista de matriculas a partir de un curso especificado
     * @param id
     * @return lista de matriculas con ese curso
     */
    public List<Matricula> findAllByidCurso (Integer id);
}
