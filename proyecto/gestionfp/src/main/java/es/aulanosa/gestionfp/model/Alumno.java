package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Alumnos")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(max = 11, message = "El idCurso no puede tener más de 100 caracteres")
    private int idCurso;
    @NotNull
    @Size(max = 11, message = "El idEstudios no puede tener más de 100 caracteres")
    private int idEstudios;
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;
    @NotNull
    private char cv;
    @NotNull
    private char carta;
    @NotNull
    @Size(max = 11, message = "El idEmpresa no puede tener más de 100 caracteres")
    private int idEmpresa;
    private Date inicioPr;
    private Date finPr;

    public Alumno(int idCurso, int idEstudios, String nombre, char cv, char carta, int idEmpresa, Date inicioPr, Date finPr) {
        this.idCurso = idCurso;
        this.idEstudios = idEstudios;
        this.nombre = nombre;
        this.cv = cv;
        this.carta = carta;
        this.idEmpresa = idEmpresa;
        this.inicioPr = inicioPr;
        this.finPr = finPr;
    }
}
