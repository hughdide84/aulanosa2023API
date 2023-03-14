package es.aulanosa.gestionfp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorEstudiosDto {

    private String error;

    private String mensaje;
}
