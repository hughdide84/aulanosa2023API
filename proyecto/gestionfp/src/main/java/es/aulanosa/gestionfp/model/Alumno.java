package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
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
    private int idCurso;
    private int idEstudios;
    
    private String nombre;
    private char cv;
    private char carta;
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
