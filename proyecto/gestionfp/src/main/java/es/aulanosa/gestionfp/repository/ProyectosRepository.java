package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyectos, Integer> {

}
