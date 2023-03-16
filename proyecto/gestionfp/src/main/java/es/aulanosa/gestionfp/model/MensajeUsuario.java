package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MensajesUsuarios")
@NoArgsConstructor //lombok - Crea
@AllArgsConstructor
@Data
public class MensajeUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "idMensaje")
    @NotNull
    private Integer idMensaje;
    @Column(name = "idUsuario")
    @NotNull
    private Integer idUsuario;
    @Column(name = "estado")
    @NotNull
    private char estado;

    public MensajeUsuario(Integer idMensaje, Integer idUsuario, char estado) {
        this.idMensaje = idMensaje;
        this.idUsuario = idUsuario;
        this.estado = estado;
    }
}
