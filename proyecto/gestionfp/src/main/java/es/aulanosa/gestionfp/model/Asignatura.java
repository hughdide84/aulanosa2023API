package es.aulanosa.gestionfp.model;

import es.aulanosa.gestionfp.repository.EstudiosRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Asignatura")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idCurso")
    private int idCurso;
    @Column(name = "idEstudios")
    private int idEstudios;
    @Column(name = "nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;
    @Column(name = "nivel")
    private int nivel;

    @ManyToOne
    @JoinColumn(name = "idCurso", insertable = false, updatable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "idEstudios", insertable = false, updatable = false)
    private Estudios estudios;


    public Asignatura(int idCurso, int idEstudios, String nombre, int nivel) {
        this.idCurso = idCurso;
        this.idEstudios = idEstudios;
        this.nombre = nombre;
        this.nivel = nivel;
    }



}
