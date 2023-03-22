package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Mensaje;
import es.aulanosa.gestionfp.model.MensajeUsuario;
import es.aulanosa.gestionfp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaz que extiende de JpaRepository para la entidad MensajeUsuario
 */
@Repository
public interface MensajeUsuarioRepository extends JpaRepository<MensajeUsuario, Integer> {
    /**
     * Devuelve una lista de mensajes que ha enviado un usuario
     * @param usuarioId
     * @return una lista de mensajes
     */
    @Query("SELECT m FROM Mensaje m WHERE m.idUsuario = ?1")
    List<Mensaje> listarPorAutor(int usuarioId);

    /**
     * Devuelve una lista de usuarios que han recibido un mensaje
     * @param mensajeId
     * @return una lista de usuarios
     */
    @Query("SELECT u FROM Usuario u join fetch u.mensajeUsuario mu WHERE mu.mensaje.id = ?1" )
    List<Usuario> listarPorDestinatario(int mensajeId);




}
