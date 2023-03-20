package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.AlumnoExterno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoExternoDTO {
    private int id;
    private int idCurso;
    private char tipo;
    private String nombre;
    private String email;
    private String telefono;
    private String universidad;
    private String titulacion;
    private String especialidad;
    private Date inicio;
    private Date fin;
    private char cv;
    private char convenio;
    private char evaluacion;
    private char horario;

    public AlumnoExterno convertirModel(){
        AlumnoExterno alumnosExternos = new AlumnoExterno();
        alumnosExternos.setId(this.getId());
        alumnosExternos.setTelefono(this.getTelefono());
        alumnosExternos.setEvaluacion(this.getEvaluacion());
        alumnosExternos.setTipo(this.getTipo());
        alumnosExternos.setTitulacion(this.getTitulacion());
        alumnosExternos.setUniversidad(this.getUniversidad());
        alumnosExternos.setHorario(this.getHorario());
        alumnosExternos.setCv(this.getCv());
        alumnosExternos.setFin(this.getFin());
        alumnosExternos.setInicio(this.getInicio());
        alumnosExternos.setEspecialidad(this.getEspecialidad());
        alumnosExternos.setNombre(this.getNombre());
        alumnosExternos.setEmail(this.getEmail());
        alumnosExternos.setConvenio(this.getConvenio());
        alumnosExternos.setIdCurso(this.getIdCurso());

        return alumnosExternos;
    }

    public AlumnoExternoDTO crearDTO(AlumnoExterno alumnosExternos){
        this.setId(alumnosExternos.getId());
        this.setTelefono(alumnosExternos.getTelefono());
        this.setEvaluacion(alumnosExternos.getEvaluacion());
        this.setTipo(alumnosExternos.getTipo());
        this.setTitulacion(alumnosExternos.getTitulacion());
        this.setUniversidad(alumnosExternos.getUniversidad());
        this.setHorario(alumnosExternos.getHorario());
        this.setCv(alumnosExternos.getCv());
        this.setFin(alumnosExternos.getFin());
        this.setInicio(alumnosExternos.getInicio());
        this.setEspecialidad(alumnosExternos.getEspecialidad());
        this.setNombre(alumnosExternos.getNombre());
        this.setEmail(alumnosExternos.getEmail());
        this.setConvenio(alumnosExternos.getConvenio());
        this.setIdCurso(alumnosExternos.getIdCurso());

        return this;

    }




}
