package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Comentarios")
@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 * Modelo de la table Comentarios de la BD
 */
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sistema")
    @NotNull(message = "El campo sistema no puede contener valores nulos")
    private char sistema;
    @Column(name = "referencia")
    @NotNull(message = "El campo referencia no puede contener valores nulos")
    private int referencia;
    @Column(name = "texto")
    @NotBlank(message = "El texto no puede estar vacío")
    @Size(max = 500, message = "El texto no puede tener más de 500 caracteres")
    @NotNull(message = "El campo texto no puede contener valores nulos")
    private String texto;
    @Column(name = "idUsuarioComentario")
    @NotNull(message = "El campo idUsuarioComentario no puede contener valores nulos")
    private Integer idUsuarioComentario;
    @Column(name = "fecha")
    @NotNull(message = "El campo fecha no puede contener valores nulos")
    private Date fecha;

    public Comentario(char sistema, int referencia, String texto, Date fecha) {
        this.sistema = sistema;
        this.referencia = referencia;
        this.texto = texto;
        this.fecha = fecha;
    }

    public Comentario(char sistema, int referencia, String texto, Integer idUsuarioComentario, Date fecha) {
        this.sistema = sistema;
        this.referencia = referencia;
        this.texto = texto;
        this.idUsuarioComentario = idUsuarioComentario;
        this.fecha = fecha;
    }
}
