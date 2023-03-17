package es.aulanosa.gestionfp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Mensajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column (name = "idUsuario")
    @NotNull
    private int idUsuario;
    @Column (name = "asunto")
    @NotNull
    private String asunto;
    @Column (name = "texto")
    @Size(max = 200, message = "El nombre debe tener como maximo 200 caracteres")
    @NotNull
    private String texto;
    @Column (name = "fecha")
    @NotNull
    private Timestamp fecha;
    @JsonIgnore
    @OneToMany(mappedBy = "mensaje")
    private List<MensajeUsuario> mensajeUsuario;

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", asunto='" + asunto + '\'' +
                ", texto='" + texto + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
