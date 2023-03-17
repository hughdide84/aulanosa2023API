package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "Matriculas")
@NoArgsConstructor //lombok - Crea
@AllArgsConstructor
@Data
public class Matriculas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "factura")
    @NotBlank(message = "Este campo no puede quedar vacio")
    @Size(min = 1, max = 100, message = "La longitud de este campo esta comprendida entre 1 y 100 caracteres")
    @NotNull
    private String factura;

    @Column(name = "nombre")
    @NotBlank(message = "Este campo no puede quedar vacio")
    @Size(min = 1, max = 100, message = "La longitud de este campo esta comprendida entre 1 y 100 caracteres")
    @NotNull
    private String nombre;

    @Column(name = "nif")
    @NotBlank(message = "Este campo no puede quedar vacio")
    @Size(min = 1, max = 20, message = "La longitud de este campo esta comprendida entre 1 y 20 caracteres")
    @NotNull
    private String nif;

    @Column(name = "cuota")
    @NotNull
    private float cuota;

    @Column(name = "matricula")
    @NotNull
    private float matricula;

    @Column(name = "idCurso")
    @NotNull
    private Integer idCurso;

    @Column(name = "observaciones")
    @NotBlank(message = "Este campo no puede quedar vacio")
    @Size(min = 1, max = 200, message = "La longitud de este campo esta comprendida entre 1 y 200 caracteres")
    private String observaciones;

    @Column(name = "fechaBaja")
    private Timestamp fechaBaja;

    @Column(name = "idUsuario")
    @NotNull
    private Integer idUsuario;

    @Column(name = "fecha")
    @NotNull
    private Timestamp fecha;

    @ManyToOne
    @JoinColumn(name = "idCurso", insertable = false, updatable = false)
    private Curso curso;
}
