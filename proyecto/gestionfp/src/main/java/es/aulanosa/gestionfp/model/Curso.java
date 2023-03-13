package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

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
        private Integer id;
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
        private String nombre;
        private Timestamp inicio;
        private Timestamp fin;
        @NotBlank(message = "El estado no puede estar vacío")
        @Size(max = 1, message = "El nombre no puede tener más de 1 caracter")
        private char estado;

        //constructor sin id
        public Curso(String nombre, Timestamp inicio, Timestamp fin, char estado) {
                this.nombre = nombre;
                this.inicio = inicio;
                this.fin = fin;
                this.estado = estado;
        }
}
