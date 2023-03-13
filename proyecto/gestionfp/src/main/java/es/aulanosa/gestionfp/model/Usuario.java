package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "password")
    private String password;
    @Column(name = "rol")
    private String rol;
    @Column(name = "email")
    private String email;

    public Usuario(String nombre, String password, String rol, String email) {
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
        this.email = email;
    }
}
