package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.BatchSize;

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
    @NonNull
    private Integer idAlumno;
    @Column(name = "documento")
    @NonNull
    private char documento;
    @Column(name = "presentacion")
    @NonNull
    private char presentacion;
    @Column(name = "notaDoc")
    @NonNull
    private Integer notaDoc;
    @Column(name = "notaPres")
    private Integer notaPres;
    @Column(name = "notaFinal")
    private Integer notaFinal;

    public Proyectos(Integer idAlumno, char documento, char presentacion, Integer notaDoc, Integer notaPres, Integer notaFinal) {
        this.idAlumno = idAlumno;
        this.documento = documento;
        this.presentacion = presentacion;
        this.notaDoc = notaDoc;
        this.notaPres = notaPres;
        this.notaFinal = notaFinal;
    }
}