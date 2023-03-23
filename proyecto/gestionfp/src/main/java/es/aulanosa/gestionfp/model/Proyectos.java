package es.aulanosa.gestionfp.model;

import es.aulanosa.gestionfp.validator.EstadoProyectoConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;

import java.sql.Timestamp;

//Constructor

/**
 * Clase modelo para especificar los campos de la tabla Proyectos
 */
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

    @Column(name = "tutoria1")
    private Timestamp tutoria1;
    @Column(name = "tutoria2")
    private Timestamp tutoria2;
    @Column(name = "tutoria3")
    private Timestamp tutoria3;

    @Column(name = "estadoTutoria1")
    @EstadoProyectoConstraint
    private Character estadoTutoria1;
    @Column(name = "estadoTutoria2")
    @EstadoProyectoConstraint
    private Character estadoTutoria2;
    @Column(name = "estadoTutoria3")
    @EstadoProyectoConstraint
    private Character estadoTutoria3;



}
