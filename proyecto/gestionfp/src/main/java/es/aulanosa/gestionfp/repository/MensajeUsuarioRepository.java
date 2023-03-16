package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Mensaje;
import es.aulanosa.gestionfp.model.MensajeUsuario;
import es.aulanosa.gestionfp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeUsuarioRepository extends JpaRepository<MensajeUsuario, Integer> {

    @Query("SELECT m FROM Mensaje m WHERE m.idUsuario = ?1")
    List<Mensaje> listarPorAutor(int usuarioId);

    @Query("SELECT u FROM Usuario u join fetch u.mensajeUsuario mu WHERE mu.mensaje.id = ?1" )
    List<Usuario> listarPorDestinatario(int mensajeId);




}
