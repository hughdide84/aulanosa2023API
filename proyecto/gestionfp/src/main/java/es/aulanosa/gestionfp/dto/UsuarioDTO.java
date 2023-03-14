package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String password;
    private String rol;
    private String email;

    public Usuario toModel() {
        Usuario usuario = new Usuario();

        usuario.setId(this.id);
        usuario.setNombre(this.nombre);
        usuario.setPassword(this.password);
        usuario.setRol(this.rol);
        usuario.setEmail(this.email);

        return usuario;
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        this.setId(usuario.getId());
        this.setNombre(usuario.getNombre());
        this.setPassword(usuario.getPassword());
        this.setRol(usuario.getRol());
        this.setEmail(usuario.getEmail());

        return this;
    }
}
