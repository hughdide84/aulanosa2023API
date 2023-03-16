package es.aulanosa.gestionfp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AlumnosEmpresas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idAlumno")
    private int idAlumno;
    @Column(name = "idEmpresa")
    private int idEmpresa;
    @Column(name = "estado")
    private char estado;

    @ManyToOne
    @JoinColumn(name = "idAlumno", insertable = false, updatable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", insertable = false, updatable = false)
    private Empresa empresa;

    public AlumnoEmpresa(int idAlumno, int idEmpresa, char estado) {
        this.idAlumno = idAlumno;
        this.idEmpresa = idEmpresa;
        this.estado = estado;
    }
}
