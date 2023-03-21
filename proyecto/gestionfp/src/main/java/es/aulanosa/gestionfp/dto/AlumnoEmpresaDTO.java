package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.AlumnoExterno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoEmpresaDTO {

    private int id;
    private int idAlumno;
    private String nombreAlumno;
    private int idEmpresa;
    private String nombreEmpresa;
    private char estado;

    public AlumnoEmpresa convertirModel(){
        AlumnoEmpresa alumnoEmpresa = new AlumnoEmpresa();
        alumnoEmpresa.setId(this.getId());
        alumnoEmpresa.setIdAlumno(this.getIdAlumno());
        alumnoEmpresa.setIdEmpresa(this.getIdEmpresa());
        alumnoEmpresa.setEstado(this.getEstado());

        return alumnoEmpresa;
    }

    public AlumnoEmpresaDTO crearDTO(AlumnoEmpresa alumnoEmpresa){
        this.setId(alumnoEmpresa.getId());
        this.setIdAlumno(alumnoEmpresa.getIdAlumno());
        this.setNombreAlumno(alumnoEmpresa.getAlumno().getNombre());
        this.setIdEmpresa(alumnoEmpresa.getIdEmpresa());
        this.setNombreEmpresa(alumnoEmpresa.getEmpresa().getNombre());
        this.setEstado(alumnoEmpresa.getEstado());

        return this;

    }
}
