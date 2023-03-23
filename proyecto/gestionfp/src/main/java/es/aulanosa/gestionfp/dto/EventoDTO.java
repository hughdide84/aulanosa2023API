package es.aulanosa.gestionfp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {

    private String texto;
    private Date fecha;


}
