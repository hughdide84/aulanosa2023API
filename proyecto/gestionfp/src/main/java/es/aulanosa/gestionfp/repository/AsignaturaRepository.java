package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfaz que extiende de JpaRepository y permite acceder a las operaciones CRUD de la entidad Asignatura en la base de datos.
 */

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {

    /**
     Método que retorna una lista de asignaturas cuyo nombre contenga una cadena de texto determinada.
     @param nombre Cadena de texto que se busca en el nombre de las asignaturas.
     @return Lista de asignaturas que contienen la cadena de texto buscada.
     */
    List<Asignatura> findAllByNombreContaining(String nombre);
}
