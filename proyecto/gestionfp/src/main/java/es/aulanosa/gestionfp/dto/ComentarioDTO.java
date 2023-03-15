package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Comentario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {
    private Integer id;
    private char sistema;
    private int referencia;
    private String texto;
    private Integer idUsuarioComentario;
    private Date fecha;

    public Comentario toModel() {
        Comentario comentario = new Comentario();

        comentario.setId(this.id);
        comentario.setSistema(this.sistema);
        comentario.setReferencia(this.referencia);
        comentario.setTexto(this.texto);
        comentario.setIdUsuarioComentario(this.idUsuarioComentario);
        comentario.setFecha(this.fecha);

        return comentario;
    }

    public ComentarioDTO toDTO(Comentario comentario) {
        this.setId(comentario.getId());
        this.setSistema(comentario.getSistema());
        this.setReferencia(comentario.getReferencia());
        this.setTexto(comentario.getTexto());
        this.setIdUsuarioComentario(comentario.getIdUsuarioComentario());
        this.setFecha(comentario.getFecha());

        return this;
    }
}
