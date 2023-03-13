package es.aulanosa.gestionfp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Estudios")
@NoArgsConstructor //lombok - Crea
@AllArgsConstructor
@Data
public class Estudios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "El id no puede estar vacío")
    @Size(min = 1, max = 10, message = "El id debe tener entre 1 y 10 caracteres")
    @Column(name = "id")
    private Integer id;

    @Column (name = "nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
    private String nombre;

    @Column (name = "fct")
    @NotBlank(message = "El fct no puede estar vacío")
    private boolean fct;

    @Column (name = "pext")
    @NotBlank(message = "El pext no puede estar vacío")
    private boolean pext;

}
