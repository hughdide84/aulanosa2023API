package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Estudios;
import es.aulanosa.gestionfp.model.Mensaje;
import es.aulanosa.gestionfp.model.MensajeUsuario;
import es.aulanosa.gestionfp.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MensajeUsuarioService {

    public MensajeUsuario insertar(MensajeUsuario mensajeUsuario);

    public Optional<MensajeUsuario> consultarPorId(Integer id);

    public List<MensajeUsuario> consultarTodos();

    public MensajeUsuario modificar(MensajeUsuario mensajeUsuario) throws NoSuchFieldException;

    public void eliminar(Integer id);

    List<Mensaje> buscarTodoMensajePorUsuarioId(int usuarioId);
    List<Usuario> buscarTodoUsuarioPorMensajeId(int mensajeId);

}
