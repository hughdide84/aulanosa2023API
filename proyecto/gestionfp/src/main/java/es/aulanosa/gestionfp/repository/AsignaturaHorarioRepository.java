package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.AsignaturaHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AsignaturaHorarioRepository extends JpaRepository<AsignaturaHorario, Integer> {
    //busca los horarios que se relacionan con la asignatura con el id que se le pase

    @Query("")
    public List<AsignaturaHorario> findByIdAsignatura(int idAsignatura);
}
