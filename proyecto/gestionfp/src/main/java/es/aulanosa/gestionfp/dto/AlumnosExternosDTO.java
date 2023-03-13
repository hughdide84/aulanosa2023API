package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.AlumnosExternos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnosExternosDTO {
    private int id;
    private int idCurso;
    private char tipo;
    private String nombre;
    private String email;
    private String telefono;
    private String universidad;
    private String titulacion;
    private String especialidad;
    private Timestamp inicio;
    private Timestamp fin;
    private char cv;
    private char convenio;
    private char evaluacion;
    private char horario;

    public AlumnosExternos convertirModel(){
        AlumnosExternos alumnosExternos = new AlumnosExternos();
        
    }


}
