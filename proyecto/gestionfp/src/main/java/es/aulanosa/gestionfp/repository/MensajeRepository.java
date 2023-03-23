package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase repositorio para las querys sobre la clase Mensajes, al extender de JPA no hace falta especificar querys básicas
 */
@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {


}