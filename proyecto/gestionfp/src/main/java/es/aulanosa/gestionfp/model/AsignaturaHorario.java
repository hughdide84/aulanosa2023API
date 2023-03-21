package es.aulanosa.gestionfp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.websocket.OnError;
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

    @ManyToOne
    @JoinColumn(name = "idAsignatura", insertable = false, updatable = false)
    private Asignatura asignatura;

}
