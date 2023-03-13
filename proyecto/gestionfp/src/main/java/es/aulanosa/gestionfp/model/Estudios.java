package es.aulanosa.gestionfp.model;


import jakarta.persistence.*;
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
    @Column(name = "id")
    private Integer id;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "fct")
    private boolean fct;

    @Column (name = "pext")
    private boolean pext;

}
