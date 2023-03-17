package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "Pagos")
@NoArgsConstructor //lombok - Crea
@AllArgsConstructor
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column (name = "idMatricula")
    @NotNull
    private int idMatricula;
    @Column (name = "recibo")
    @Size(max = 20, message = "El nombre debe tener entre 1 y 50 caracteres")
    @NotNull
    private String recibo;
    @Column (name = "pago")
    @NotNull
    private float pago;
    @Column (name = "estado")
    @NotNull
    private char estado;
    @Column (name = "recibo")
    @Size(max = 200, message = "Observaciones no puede tener mas de 200 caracteres")
    private String observaciones;
    @Column (name = "idUsuario")
    @NotNull
    private int idUsuario;
    @Column (name = "fecha")
    @NotNull
    private Timestamp fecha;

}
