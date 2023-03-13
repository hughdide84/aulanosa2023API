package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Empresas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idCurso")
    @NotBlank(message = "El idCurso no puede estar vacío")
    private int idCurso;
    @Column(name = "idEstudios")
    @NotBlank(message = "El idEstudios no puede estar vacío")
    private int idEstudios;
    @Column(name = "nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;
    @Column(name = "direccionSocial")
    @NotBlank(message = "La dirección social no puede estar vacía")
    @Size(max = 300, message = "La dirección social no puede tener más de 300 caracteres")
    private String direccionSocial;
    @Column(name = "direccionTrabajo")
    @NotBlank(message = "La dirección de trabajo no puede estar vacía")
    @Size(max = 300, message = "La dirección de trabajo no puede tener más de 300 caracteres")
    private String direccionTrabajo;
    @Column(name = "cif")
    @NotBlank(message = "El cif no puede estar vacío")
    @Size(max = 10, message = "El cif no puede tener más de 10 caracteres")
    private String cif;
    @Column(name = "representante")
    @NotBlank(message = "El representante no puede estar vacío")
    @Size(max = 100, message = "El representante no puede tener más de 100 caracteres")
    private String representante;
    @Column(name = "contacto")
    @NotBlank(message = "El contacto no puede estar vacío")
    @Size(max = 100, message = "El contacto no puede tener más de 100 caracteres")
    private String contacto;
    @Column(name = "tutor1")
    @NotBlank(message = "El tutor1 no puede estar vacío")
    @Size(max = 100, message = "El tutor1 no puede tener más de 100 caracteres")
    private String tutor1;
    @Column(name = "tutor2")
    @NotBlank(message = "El tutor2 no puede estar vacío")
    @Size(max = 100, message = "El tutor2 no puede tener más de 100 caracteres")
    private String tutor2;
    @Column(name = "tutor3")
    @NotBlank(message = "El tutor3 no puede estar vacío")
    @Size(max = 100, message = "El tutor3 no puede tener más de 100 caracteres")
    private String tutor3;
    @Column(name = "convenio")
    @NotBlank(message = "El convenio no puede estar vacío")
    private char convenio;
    @Column(name = "planIndividual")
    @NotBlank(message = "El planIndividual no puede estar vacío")
    private char planIndividual;
    @Column(name = "hojaObersevaciones")
    @NotBlank(message = "La hojaObersevaciones no puede estar vacía")
    private char hojaObersevaciones;

    public Empresa(int idCurso, int idEstudios, String nombre, String direccionSocial, String direccionTrabajo, String cif, String representante, String contacto, String tutor1, String tutor2, String tutor3, char convenio, char planIndividual, char hojaObersevaciones) {
        this.idCurso = idCurso;
        this.idEstudios = idEstudios;
        this.nombre = nombre;
        this.direccionSocial = direccionSocial;
        this.direccionTrabajo = direccionTrabajo;
        this.cif = cif;
        this.representante = representante;
        this.contacto = contacto;
        this.tutor1 = tutor1;
        this.tutor2 = tutor2;
        this.tutor3 = tutor3;
        this.convenio = convenio;
        this.planIndividual = planIndividual;
        this.hojaObersevaciones = hojaObersevaciones;
    }

}
