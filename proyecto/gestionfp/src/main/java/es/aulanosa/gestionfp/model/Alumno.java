package es.aulanosa.gestionfp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Column(name = "usuario")
    private String usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "idAlumno",cascade = CascadeType.REMOVE)
    private List<AlumnoEmpresa> alumnoEmpresa;
    @ManyToOne
    @JoinColumn(name = "idCurso", insertable = false, updatable = false)
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "idEstudios", insertable = false, updatable = false)
    private Estudios estudios;
    @ManyToOne
    @JoinColumn(name = "idEmpresa", insertable = false, updatable = false)
    private Empresa empresa;

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

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", idCurso=" + idCurso +
                ", idEstudios=" + idEstudios +
                ", nombre='" + nombre + '\'' +
                ", cv=" + cv +
                ", carta=" + carta +
                ", idEmpresa=" + idEmpresa +
                ", inicioPr=" + inicioPr +
                ", finPr=" + finPr +
                '}';
    }
}
