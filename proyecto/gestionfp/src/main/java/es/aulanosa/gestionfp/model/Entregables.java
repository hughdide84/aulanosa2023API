package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Entity
@Table(name = "Entregables")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entregables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private char tipo;
    @Column(name = "idAsignatura")
    @NotNull(message = "El id de la asignatura no puede estar vacío")
    private int idAsignatura;
    private String descripcion;
    private Timestamp fecha;


}
