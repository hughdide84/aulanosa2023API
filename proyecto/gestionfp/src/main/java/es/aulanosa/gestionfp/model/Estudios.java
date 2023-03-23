package es.aulanosa.gestionfp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la tabla Estudios de la base de datos
 */
@Entity
@Table(name = "Estudios")
@NoArgsConstructor //lombok - Crea
@AllArgsConstructor
@Data
public class Estudios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column (name = "nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
    @NotNull
    private String nombre;

    @Column (name = "fct")
    @NotNull
    private boolean fct;

    @Column (name = "pext")
    @NotNull
    private boolean pext;

}
