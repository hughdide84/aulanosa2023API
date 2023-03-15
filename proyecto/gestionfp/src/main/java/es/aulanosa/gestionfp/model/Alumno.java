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
    @Column(name = "id")
    private int id;
    @NotNull
    @Column(name = "idCurso")
    private int idCurso;
    @NotNull
    @Column(name = "idEstudios")
    private int idEstudios;
    @NotNull
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(name = "nombre")
    private String nombre;
    @NotNull
    @Column(name = "cv")
    private char cv;
    @NotNull
    @Column(name = "carta")
    private char carta;
    @Column(name = "idEmpresa")
    private int idEmpresa;
    @Column(name = "inicioPr")
    private Date inicioPr;
    @Column(name = "finPr")
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
