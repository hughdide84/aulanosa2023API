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
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sistema")
    @NotNull
    private char sistema;
    @Column(name = "referencia")
    @NotNull
    private int referencia;
    @Column(name = "texto")
    @NotBlank(message = "El texto no puede estar vacío")
    @Size(max = 500, message = "El texto no puede tener más de 500 caracteres")
    @NotNull
    private String texto;
    @Column(name = "fecha")
    @NotNull
    private Date fecha;

    public Comentario(char sistema, int referencia, String texto, Date fecha) {
        this.sistema = sistema;
        this.referencia = referencia;
        this.texto = texto;
        this.fecha = fecha;
    }
}
