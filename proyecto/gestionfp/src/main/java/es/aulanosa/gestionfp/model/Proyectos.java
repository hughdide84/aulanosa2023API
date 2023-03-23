package es.aulanosa.gestionfp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;

import java.sql.Timestamp;

//Constructor
@Entity
@Table(name = "Proyectos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Proyectos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "idAlumno")
    @NotNull(message = "Este campo no puede quedar vacio")
    private Integer idAlumno;
    @Column(name = "documento")
    @NotNull(message = "Este campo no puede quedar vacio")
    private char documento;
    @Column(name = "presentacion")
    @NotNull(message = "Este campo no puede quedar vacio")
    private char presentacion;
    @Column(name = "notaDoc")
    private Integer notaDoc;
    @Column(name = "notaPres")
    private Integer notaPres;
    @Column(name = "notaFinal")
    private Integer notaFinal;
    @Column(name = "exposicion")
    private Timestamp exposicion;

    @ManyToOne
    @JoinColumn(name = "idAlumno", insertable = false, updatable = false)
    private Alumno alumno;

    public Proyectos(Integer idAlumno, char documento, char presentacion, Integer notaDoc, Integer notaPres, Integer notaFinal, Timestamp exposicion) {
        this.idAlumno = idAlumno;
        this.documento = documento;
        this.presentacion = presentacion;
        this.notaDoc = notaDoc;
        this.notaPres = notaPres;
        this.notaFinal = notaFinal;
        this.exposicion = exposicion;
    }

    @Override
    public String toString() {
        return "Proyectos{" +
                "id=" + id +
                ", idAlumno=" + idAlumno +
                ", documento=" + documento +
                ", presentacion=" + presentacion +
                ", notaDoc=" + notaDoc +
                ", notaPres=" + notaPres +
                ", notaFinal=" + notaFinal +
                ", exposicion=" + exposicion +
                '}';
    }
}
