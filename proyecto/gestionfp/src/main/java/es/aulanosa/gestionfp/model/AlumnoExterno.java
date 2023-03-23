package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
/**
 * Clase con las propiedades de la tabla AlumnoExterno
 */
@Entity
@Table(name = "AlumnosExternos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoExterno {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El idCurso no puede estar vacío")
    @Column(name = "idCurso")
    private int idCurso;

    @NotNull(message = "El tipo no puede estar vacío")
    @Column(name = "tipo")
    private char tipo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 50, message = "El email no puede tener más de 50 caracteres")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "El telefono no puede estar vacío")
    @Size(max = 16, message = "El telefono no puede tener más de 16 caracteres")
    @Column(name = "telefono")
    private String telefono;

    @NotBlank(message = "El campo universidad no puede estar vacío")
    @Size(max = 100, message = "La universidad no puede tener más de 100 caracteres")
    @Column(name = "universidad")
    private String universidad;

    @NotBlank(message = "La titulacion no puede estar vacía")
    @Size(max = 100, message = "La titulacion no puede tener más de 100 caracteres")
    @Column(name = "titulacion")
    private String titulacion;

    @NotBlank(message = "La especialidad no puede estar vacía")
    @Size(max = 100, message = "La especialidad no puede tener más de 100 caracteres")
    @Column(name = "especialidad")
    private String especialidad;

    @Column(name = "inicio")
    private Date inicio;

    @Column(name = "fin")
    private Date fin;

    @NotNull(message = "El cv no puede estar vacío")
    @Column(name = "cv")
    private char cv;

    @NotNull(message = "El convenio no puede estar vacío")
    @Column(name = "convenio")
    private char convenio;

    @NotNull(message = "La evaluacion no puede estar vacía")
    @Column(name = "evaluacion")
    private char evaluacion;

    @NotNull(message = "El horario no puede estar vacío")
    @Column(name = "horario")
    private char horario;


}
