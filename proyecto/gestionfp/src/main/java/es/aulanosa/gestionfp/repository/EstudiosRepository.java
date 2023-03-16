package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Estudios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudiosRepository extends JpaRepository<Estudios, Integer> {

}
