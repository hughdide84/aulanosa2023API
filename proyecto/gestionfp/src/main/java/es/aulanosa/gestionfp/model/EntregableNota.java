package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EntregablesNotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregableNota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "idEntregable")
    @NotNull(message = "El idEntregable no puede estar vacío")
    private Integer idEntregable;

    @Column(name = "idAlumno")
    @NotNull(message = "El idAlumno no puede estar vacío")
    private Integer idAlumno;

    @Column(name = "nota")
    @NotNull(message = "La nota no puede estar vacía")
    private float nota;
}
