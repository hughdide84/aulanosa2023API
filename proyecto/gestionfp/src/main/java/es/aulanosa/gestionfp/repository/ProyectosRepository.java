package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Clase repository para las querys necesarias para la realización de las APIS, al extender de JPA los métodos de JPA se tienen en esta clase
 */
@Repository
public interface ProyectosRepository extends JpaRepository<Proyectos, Integer> {

}
