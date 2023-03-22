package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.MensajeUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la tabla MensajesUsuarios
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeUsuarioDTO {

    private Integer id;

    private Integer idMensaje;

    private Integer idUsuario;

    private char estado;

    public MensajeUsuarioDTO(Integer idMensaje, Integer idUsuario, char estado) {
        this.idMensaje = idMensaje;
        this.idUsuario = idUsuario;
        this.estado = estado;
    }

    public MensajeUsuario convertirModel(){
        MensajeUsuario mensajeUsuario = new MensajeUsuario();
        mensajeUsuario.setId(this.id);
        mensajeUsuario.setIdMensaje(this.idMensaje);
        mensajeUsuario.setIdUsuario(this.idUsuario);
        mensajeUsuario.setEstado(this.estado);
        return mensajeUsuario;
    }

    public MensajeUsuarioDTO crearDTO(MensajeUsuario mensajeUsuario){
        this.setId(mensajeUsuario.getId());
        this.setIdMensaje(mensajeUsuario.getIdMensaje());
        this.setIdUsuario(mensajeUsuario.getIdUsuario());
        this.setEstado(mensajeUsuario.getEstado());
        return this;
    }
}
