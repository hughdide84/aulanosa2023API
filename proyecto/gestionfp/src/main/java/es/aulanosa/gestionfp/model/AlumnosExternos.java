package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "AlumnosExternos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnosExternos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
