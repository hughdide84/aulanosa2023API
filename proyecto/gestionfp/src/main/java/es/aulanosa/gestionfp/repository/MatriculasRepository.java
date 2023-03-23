package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculasRepository extends JpaRepository<Matricula,Integer> {

    @Query("SELECT m FROM Matricula m WHERE m.nombre = ?1")
    public List<Matricula> buscarPorNombre (String nombre);

    @Query("SELECT m FROM Matricula m WHERE month(fecha) = ?1")
    public List<Matricula> buscarPorMes (Integer mes);

    @Query("SELECT m from Matricula m WHERE saldo < 0 AND idCurso = ?1")
    public List<Matricula> buscaridCursoSaldoNegativo(Integer idCurso);

    public List<Matricula> findAllByidCurso (Integer id);
}
