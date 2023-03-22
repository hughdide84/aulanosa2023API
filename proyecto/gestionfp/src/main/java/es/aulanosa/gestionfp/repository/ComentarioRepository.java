package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la recuperación de datos de Comentario, extiende de JPA, lo que hace que muchos de los métodos no sea necesario especificar una query
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    /**
     * Al extender JPA crea una query personalizada pasandole los parámetros especificados en el nombre de la función
     * @param sistema Variable char que se le pasa a la query
     * @param refencia Variable int que se le pasa a la query
     * @return Devuelve una lista de comentarios listados por el sistema y referencia pasados como parámetros
     */
    List<Comentario> findBySistemaAndReferencia(char sistema, int refencia);

    /**
     * Query autogenerada por JPA a la que se le pasan los parametros sistema e ID
     * @param sistema Variable char que se le pasa a la query
     * @param id Variable representante del campo ID de la BD
     * @return Devuelve na lista de comentarios listados por los anteriores parámetros
     */
    List<Comentario> findBySitemaAndId(char sistema, int id);

}
