package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El idCurso no puede estar vacío")
    @Size(max = 11, message = "El idCurso no puede tener más de 11 caracteres")
    private int idCurso;

    @NotBlank(message = "El tipo no puede estar vacío")
    @Size(max = 1, message = "El tipo no puede tener más de 1 caracteres")
    private char tipo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 50, message = "El email no puede tener más de 50 caracteres")
    private String email;

    @NotBlank(message = "El telefono no puede estar vacío")
    @Size(max = 16, message = "El telefono no puede tener más de 16 caracteres")
    private String telefono;

    @NotBlank(message = "El campo universidad no puede estar vacío")
    @Size(max = 100, message = "La universidad no puede tener más de 100 caracteres")
    private String universidad;

    @NotBlank(message = "La titulacion no puede estar vacía")
    @Size(max = 100, message = "La titulacion no puede tener más de 100 caracteres")
    private String titulacion;

    @NotBlank(message = "La especialidad no puede estar vacía")
    @Size(max = 100, message = "La especialidad no puede tener más de 100 caracteres")
    private String especialidad;

    private Timestamp inicio;
    private Timestamp fin;

    @NotBlank(message = "El cv no puede estar vacío")
    @Size(max = 1, message = "El cv no puede tener más de 1 caracteres")
    private char cv;

    @NotBlank(message = "El convenio no puede estar vacío")
    @Size(max = 1, message = "El convenio no puede tener más de 1 caracteres")
    private char convenio;

    @NotBlank(message = "La evaluacion no puede estar vacía")
    @Size(max = 1, message = "La evaluacion no puede tener más de 1 caracteres")
    private char evaluacion;

    @NotBlank(message = "El horario no puede estar vacío")
    @Size(max = 1, message = "El horario no puede tener más de 1 caracteres")
    private char horario;


}
