package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.MensajeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeUsuarioRepository extends JpaRepository<MensajeUsuario, Integer> {


}
