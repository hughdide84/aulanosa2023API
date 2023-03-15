package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombre(String nombre);

    List<Usuario> findByNombreContains(String cadenaNombre);

    List<Usuario> findByEmailContains(String cadenaEmail);

    List<Usuario> findByRol(String rol);
}
