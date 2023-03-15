package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Table(name = "AsignaturaHorario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "idAsignatura")
    private int idAsignatura;

    @NotNull
    @Column(name = "dia")
    private char dia;

    @NotNull
    @Column(name = "inicio")
    private Time inicio;

    @NotNull
    @Column(name = "fin")
    private Time fin;
}
