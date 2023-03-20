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
    @NotBlank(message = "La nota no puede estar vacía")
    @NotNull
    @Size(min = 1, max = 2, message = "La nota debe tener entre 1 y 2 caracteres")
    private float nota;
}
