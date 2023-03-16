package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

    //Datos correspondientes a los que hay en la tabla cursos
    private int id;
    private String nombre;
    private Timestamp inicio;
    private Timestamp fin;
    private char estado;

    //Conversion del model al DTO
    public Curso convertirModel() {
        Curso curso = new Curso();
        curso.setId(this.id);
        curso.setNombre(this.nombre);
        curso.setInicio(this.inicio);
        curso.setFin(this.fin);
        curso.setEstado(this.estado);
        return curso;
    }

    //Creacion del DTO
    public CursoDTO crearDTO(Curso curso) {
        this.setId(curso.getId());
        this.setNombre(curso.getNombre());
        this.setInicio(curso.getInicio());
        this.setFin(curso.getFin());
        this.setEstado(curso.getEstado());
        return this;
    }

}
