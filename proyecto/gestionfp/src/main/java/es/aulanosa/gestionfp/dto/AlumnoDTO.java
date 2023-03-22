package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Alumno;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * Clase DTO para la entidad Alumno
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {

    private int id;
    private int idCurso;
    private int idEstudios;
    private String nombre;
    private char cv;
    private char carta;
    private int idEmpresa;
    private Date inicioPr;
    private Date finPr;


    public Alumno toModel(){
        Alumno alumno = new Alumno();

        alumno.setId(this.id);
        alumno.setIdCurso(this.idCurso);
        alumno.setIdEstudios(this.idEstudios);
        alumno.setNombre(this.nombre);
        alumno.setCv(this.cv);
        alumno.setCarta(this.carta);
        alumno.setIdEmpresa(this.idEmpresa);
        alumno.setInicioPr(this.inicioPr);
        alumno.setFinPr(this.finPr);

        return alumno;
    }

    public AlumnoDTO toDTO(Alumno alumno){

        this.setId(alumno.getId());
        this.setIdCurso(alumno.getIdCurso());
        this.setNombre(alumno.getNombre());
        this.setCv(alumno.getCv());
        this.setCarta(alumno.getCarta());
        this.setIdEmpresa(alumno.getIdEmpresa());
        this.setInicioPr(alumno.getInicioPr());
        this.setFinPr(alumno.getFinPr());

        return this;

    }

}
