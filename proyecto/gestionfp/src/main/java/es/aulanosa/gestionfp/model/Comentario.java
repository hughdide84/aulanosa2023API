package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
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
    private char sistema;
    @Column(name = "referencia")
    private int referencia;
    @Column(name = "texto")
    private String texto;
    @Column(name = "fecha")
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
