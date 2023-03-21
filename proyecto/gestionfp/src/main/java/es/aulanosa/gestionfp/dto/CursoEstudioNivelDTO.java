package es.aulanosa.gestionfp.dto;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoEstudioNivelDTO {

    private int idCurso;
    private int idEstudio;
    private int nivel;
    private Time fechaInicio;
    private Time fechaFin;

    public CursoEstudioNivelDTO(int idCurso, int idEstudio, int nivel) {
        this.idCurso = idCurso;
        this.idEstudio = idEstudio;
        this.nivel = nivel;
    }
}
