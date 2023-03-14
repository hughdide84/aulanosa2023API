package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;
    @Column(name = "password")
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(max = 100, message = "La contraseña no puede tener más de 100 caracteres")
    @NotNull(message = "La contraseña no puede ser nula")
    private String password;
    @Column(name = "email")
    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 50, message = "El email no puede tener más de 50 caracteres")
    @NotNull(message = "El email no puede ser nulo")
    private String email;
    @Column(name = "rol")
    @NotBlank(message = "El rol no puede estar vacío")
    @Size(max = 10, message = "El rol no puede tener más de 10 caracteres")
    @NotNull(message = "El rol no puede ser nulo")
    private String rol;

    public Usuario(String nombre, String password, String email, String rol) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }
}
