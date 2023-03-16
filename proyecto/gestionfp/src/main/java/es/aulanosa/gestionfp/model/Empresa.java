package es.aulanosa.gestionfp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @NotNull(message = "El id del curso no puede estar vacío")
    private int idCurso;
    @Column(name = "idEstudios")
    @NotNull(message = "El id de los estudios no puede estar vacío")
    private int idEstudios;
    @Column(name = "nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @NotNull(message = "El nombre no puede estar vacío")
    private String nombre;
    @Column(name = "direccionSocial")
    @NotBlank(message = "La dirección social no puede estar vacía")
    @Size(max = 300, message = "La dirección social no puede tener más de 300 caracteres")
    @NotNull(message = "El la direccion social no puede estar vacío")
    private String direccionSocial;
    @Column(name = "direccionTrabajo")
    @NotBlank(message = "La dirección de trabajo no puede estar vacía")
    @Size(max = 300, message = "La dirección de trabajo no puede tener más de 300 caracteres")
    @NotNull(message = "El la direccion de trabajo no puede estar vacío")
    private String direccionTrabajo;
    @Column(name = "cif")
    @NotBlank(message = "El cif no puede estar vacío")
    @Size(max = 10, message = "El cif no puede tener más de 10 caracteres")
    @NotNull(message = "El cif no puede estar vacío")
    private String cif;
    @Column(name = "representante")
    @NotBlank(message = "El representante no puede estar vacío")
    @Size(max = 100, message = "El representante no puede tener más de 100 caracteres")
    @NotNull(message = "El representante no puede estar vacío")
    private String representante;
    @Column(name = "contacto")
    @NotBlank(message = "El contacto no puede estar vacío")
    @Size(max = 100, message = "El contacto no puede tener más de 100 caracteres")
    @NotNull(message = "El contacto no puede estar vacío")
    private String contacto;
    @Column(name = "tutor1")
    @NotBlank(message = "El tutor1 no puede estar vacío")
    @Size(max = 100, message = "El tutor1 no puede tener más de 100 caracteres")
    @NotNull(message = "El tutor1 no puede estar vacío")
    private String tutor1;
    @Column(name = "tutor2")
    @NotBlank(message = "El tutor2 no puede estar vacío")
    @Size(max = 100, message = "El tutor2 no puede tener más de 100 caracteres")
    @NotNull(message = "El tutor2 no puede estar vacío")
    private String tutor2;
    @Column(name = "tutor3")
    @NotBlank(message = "El tutor3 no puede estar vacío")
    @Size(max = 100, message = "El tutor3 no puede tener más de 100 caracteres")
    @NotNull(message = "El tutor3 no puede estar vacío")
    private String tutor3;
    @Column(name = "convenio")
    @NotNull(message = "El convenio no puede estar vacío")
    private char convenio;
    @Column(name = "planIndividual")
    @NotNull(message = "El plan individual no puede estar vacío")
    private char planIndividual;
    @Column(name = "hojaActividades")
    @NotNull(message = "La hoja de actividades no puede estar vacía")
    private char hojaActividades;

    @JsonIgnore
    @OneToMany(mappedBy = "idEmpresa")
    private List<AlumnoEmpresa> alumnoEmpresa;

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
        this.hojaActividades = hojaObersevaciones;
    }
}
