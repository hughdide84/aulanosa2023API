package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EntregablesNotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregableNotas {

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
    @NotNull
    private float nota;
}
