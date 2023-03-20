package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "id")
    private int id;
    @Column(name = "idCurso")
    @NotNull(message = "El id del curso no puede ser nulo")
    private int idCurso;
    @Column(name = "idEstudios")
    @NotNull(message = "El id de los estudios no puede ser nulo")
    private int idEstudios;
    @Column(name = "nombre")
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;
    @Column(name = "nivel")
    @NotNull(message = "El nivel no puede ser nulo")
    private int nivel;

    public Asignatura(int idCurso, int idEstudios, String nombre, int nivel) {
        this.idCurso = idCurso;
        this.idEstudios = idEstudios;
        this.nombre = nombre;
        this.nivel = nivel;
    }

}
