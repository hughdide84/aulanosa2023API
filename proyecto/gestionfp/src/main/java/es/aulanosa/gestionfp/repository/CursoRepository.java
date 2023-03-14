package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository <Curso, Integer> {



}
