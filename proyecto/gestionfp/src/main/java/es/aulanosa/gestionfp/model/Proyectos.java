package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer idAlumno;
    private char documento;
    private char presentacion;
    private Integer notaDoc;
    private Integer notaPres;
    private Integer notaFinal;
}
