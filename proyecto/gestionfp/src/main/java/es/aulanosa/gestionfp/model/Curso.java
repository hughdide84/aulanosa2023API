package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
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
        private String nombre;
        private Timestamp inicio;
        private Timestamp fin;
        private char estado;

        //constructor sin id
        public Curso(String nombre, Timestamp inicio, Timestamp fin, char estado) {
                this.nombre = nombre;
                this.inicio = inicio;
                this.fin = fin;
                this.estado = estado;
        }
}
