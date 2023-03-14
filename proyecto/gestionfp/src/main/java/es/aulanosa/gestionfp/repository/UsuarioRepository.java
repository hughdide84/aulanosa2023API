package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findAllByNombre(String nombre);
}
