package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Asignatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaDTO {

    private int id;
    private String nombre;
    private int idCurso;
    private int idEstudios;
    private int nivel;

    public AsignaturaDTO(String nombre, int idCurso, int idEstudios, int nivel) {
        this.nombre = nombre;
        this.idCurso = idCurso;
        this.idEstudios = idEstudios;
        this.nivel = nivel;
    }


    public Asignatura toAsignatura(){
        return new Asignatura(this.id, this.idCurso, this.idEstudios, this.nombre,  this.nivel);
    }

    public AsignaturaDTO(Asignatura asignatura){
        this.id = asignatura.getId();
        this.nombre = asignatura.getNombre();
        this.idCurso = asignatura.getIdCurso();
        this.idEstudios = asignatura.getIdEstudios();
        this.nivel = asignatura.getNivel();
    }
}
