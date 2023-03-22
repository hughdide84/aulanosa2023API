package es.aulanosa.gestionfp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;
/**
 * Clase java que representa la tabla Cursos en la BD
 */

@Entity
@Table(name = "Cursos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Curso {

        //atributos objeto curso
        //id autogenerado

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
        @Column(name = "nombre")
        private String nombre;
        @Column(name = "inicio")
        private Timestamp inicio;
        @Column(name = "fin")
        private Timestamp fin;
        @NotNull
        @Column(name = "estado")
        private char estado;

        @JsonIgnore
        @OneToMany(mappedBy = "idCurso", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
        @ToString.Exclude
        private List<Matricula> matriculas;
        //constructor sin id
        /**
         * Constructor para la clase Curso sin el id.
         *
         * @param nombre Nombre del curso.
         * @param inicio Fecha de inicio del curso.
         * @param fin Fecha de fin del curso.
         * @param estado Estado del curso (activo o inactivo).
         */
        public Curso(String nombre, Timestamp inicio, Timestamp fin, char estado) {
                this.nombre = nombre;
                this.inicio = inicio;
                this.fin = fin;
                this.estado = estado;
        }
}
