package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Estudios;
import es.aulanosa.gestionfp.model.Mensaje;
import es.aulanosa.gestionfp.model.MensajeUsuario;
import es.aulanosa.gestionfp.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los métodos para la entidad MensajeUsuario
 */
public interface MensajeUsuarioService {

    /**
     * Método que inserta un mensajeUsuario en la base de datos
     * @param mensajeUsuario
     * @return un nuevo mensajeUsuario
     */
    public MensajeUsuario insertar(MensajeUsuario mensajeUsuario);
    /**
     * Método que consulta un mensajeUsuario por su id
     * @param id
     * @return el mensajeUsuario con el id indicado
     */
    public Optional<MensajeUsuario> consultarPorId(Integer id);
    /**
     * Método que consulta todos los mensajeUsuarios
     * @return una lista con todos los mensajeUsuarios
     */
    public List<MensajeUsuario> consultarTodos();
    /**
     * Método que modifica un mensajeUsuario
     * @param mensajeUsuario
     * @return el mensajeUsuario especifico que queramos modificado
     * @throws NoSuchFieldException si el mensajeUsuario no existe
     */
    public MensajeUsuario modificar(MensajeUsuario mensajeUsuario) throws NoSuchFieldException;
    /**
     * Método que elimina un mensajeUsuario
     * @param id
     * return el mensajeUsuario eliminado
     */
    public void eliminar(Integer id);
    /**
     * Método que lista los mensajes que ha enviado un usuario
     * @param usuarioId
     * @return una lista con los mensajes de un usuario
     */
    List<Mensaje> listarPorAutor(int usuarioId);
    /**
     * Método que lista los mensajes que ha recibido un usuario
     * @param mensajeId
     * @return una lista con los mensajes que ha recibido un usuario
     */
    List<Usuario> listarPorDestinario(int mensajeId);

}
