package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sistema")
    @NotBlank(message = "El sistema no puede estar vacío")
    @Size(max = 1, message = "El sistema no puede tener más de 1 caracter")
    private char sistema;
    @Column(name = "referencia")
    @NotBlank(message = "La referencia no puede estar vacía")
    private int referencia;
    @Column(name = "texto")
    @NotBlank(message = "El texto no puede estar vacío")
    @Size(max = 500, message = "El texto no puede tener más de 500 caracteres")
    private String texto;
    @Column(name = "fecha")
    @NotBlank(message = "La fecha no puede estar vacía")
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "idUsuarioComentado", referencedColumnName = "id")
    private Usuario usuarioComentado;

    public Comentario(char sistema, int referencia, String texto, Date fecha) {
        this.sistema = sistema;
        this.referencia = referencia;
        this.texto = texto;
        this.fecha = fecha;
    }

    public Comentario(char sistema, int referencia, String texto, Date fecha, Usuario usuarioComentado) {
        this.sistema = sistema;
        this.referencia = referencia;
        this.texto = texto;
        this.fecha = fecha;
        this.usuarioComentado = usuarioComentado;
    }
}
