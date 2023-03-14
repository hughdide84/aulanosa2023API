package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Usuario;
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
    private Date fecha;
    private Usuario usuarioComentado;

    
}
