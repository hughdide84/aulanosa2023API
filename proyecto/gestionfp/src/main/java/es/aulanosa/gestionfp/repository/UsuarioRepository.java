package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que extiende de JpaRepository para poder realizar las operaciones
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Busca un usuario por su nombre
     * @param nombre nombre del usuario que se desea buscar
     * @return Usuario con el nombre indicado
     */
    Optional<Usuario> findByNombre(String nombre);

    /**
     * Busca usuarios con dicho nombre contenido
     * @param cadenaNombre nombre del usuario por el que se desea buscar
     * @return Usuario con el nombre indicado contenido
     */
    List<Usuario> findByNombreContains(String cadenaNombre);

/**
     * Busca usuarios con dicho email contenido
     * @param cadenaEmail email del usuario por el que se desea buscar
     * @return Usuario con el email indicado contenido
     */
    List<Usuario> findByEmailContains(String cadenaEmail);

    /**
     * Busca usuarios con dicho rol
     * @param rol rol del usuario por el que se desea buscar
     * @return Usuario con el rol indicado
     */
    List<Usuario> findByRol(String rol);
}