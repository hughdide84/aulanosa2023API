package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Entity
@Table(name = "Entregables")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entregable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tipo")
    @NotNull(message = "El id de la asignatura no puede estar vacío")
    @Pattern(message = "Los valores solo pueden ser P o E" ,regexp = "(^([EP]){1}$)")
    private char tipo;
    @Column(name = "idAsignatura")
    @NotNull(message = "El id de la asignatura no puede estar vacío")
    private int idAsignatura;
    @Column(name = "descripcion")
    @NotNull(message = "La descripcion no puede estar vacía")
    @Size(max = 100, message = "La descripcion debe tener menos de 100 caracteres")
    private String descripcion;
    @Column(name = "fecha")
    private Timestamp fecha;

}
