package es.aulanosa.gestionfp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AlumnosEmpresas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnosEmpresas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idAlumno")
    private int idAlumno;
    @Column(name = "idEmpresa")
    private int idEmpresa;
    @Column(name = "estado")
    private char estado;

    public AlumnosEmpresas(int idAlumno, int idEmpresa, char estado) {
        this.idAlumno = idAlumno;
        this.idEmpresa = idEmpresa;
        this.estado = estado;
    }
}
