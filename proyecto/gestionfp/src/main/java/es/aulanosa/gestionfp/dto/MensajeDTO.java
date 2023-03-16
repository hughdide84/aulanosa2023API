package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Mensaje;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {

    private int id;
    private int idUsuario;
    private String asunto;
    private String texto;
    private Timestamp fecha;


    public Mensaje toModel(){

        Mensaje mensaje = new Mensaje();

        mensaje.setId(this.id);
        mensaje.setIdUsuario(this.idUsuario);
        mensaje.setAsunto(this.asunto);
        mensaje.setTexto(this.texto);
        mensaje.setFecha(this.fecha);

        return mensaje;

    }

    public MensajeDTO toDTO(Mensaje mensaje){

        this.setId(mensaje.getId());
        this.setIdUsuario(mensaje.getIdUsuario());
        this.setAsunto(mensaje.getAsunto());
        this.setTexto(mensaje.getTexto());
        this.setFecha(mensaje.getFecha());

        return this;
    }

}
