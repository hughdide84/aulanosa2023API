package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Estudios;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los métodos de la capa de servicio para la entidad Estudios.
 */
public interface EstudiosService {

    /**
     * Método para insertar un estudio.
     *
     * @param estudios
     * @return Estudios insertados.
     */
    public Estudios insertar(Estudios estudios);

    /**
     * Método para consultar un estudio por su id.
     *
     * @param id
     * @return Estudios consultado.
     */
    public Optional<Estudios> consultarPorId(Integer id);

    /**
     * Método para consultar todos los estudios.
     *
     * @return Lista de estudios.
     */
    public List<Estudios> consultarTodos();

    /**
     * Método para modificar un estudio.
     *
     * @param estudios
     * @return Estudios modificado.
     */
    public Estudios modificar(Estudios estudios) throws NoSuchFieldException;

    /**
     * Método para eliminar un estudio por su id.
     *
     * @param id
     * @return Estudios eliminado.
     */
    public void eliminar(Integer id);


}
