package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Proyectos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Clase DTO para la presentación y recepción de datos en el controller
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoDTO {

    private Integer id;

    private int idAlumno;

    private char documento;

    private char presentacion;

    private int notaDoc;

    private int notaPres;

    private int notaFinal;
    private Timestamp exposicion;

    private Timestamp tutoria1;
    private Timestamp tutoria2;
    private Timestamp tutoria3;

    private Character estadoTutoria1;
    private Character estadoTutoria2;
    private Character estadoTutoria3;

    public Proyectos toModel() {
        Proyectos proyectos = new Proyectos();

        proyectos.setId(this.id);
        proyectos.setIdAlumno(this.idAlumno);
        proyectos.setDocumento(this.documento);
        proyectos.setPresentacion(this.presentacion);
        proyectos.setNotaDoc(this.notaDoc);
        proyectos.setNotaPres(this.notaPres);
        proyectos.setNotaFinal(this.notaFinal);
        proyectos.setExposicion(this.exposicion);
        proyectos.setTutoria1(this.tutoria1);
        proyectos.setTutoria2(this.tutoria2);
        proyectos.setTutoria3(this.tutoria3);
        proyectos.setEstadoTutoria1(this.estadoTutoria1);
        proyectos.setEstadoTutoria2(this.estadoTutoria2);
        proyectos.setEstadoTutoria3(this.estadoTutoria3);

        return proyectos;
    }

    public ProyectoDTO toDTO(Proyectos proyectos) {
        this.setId(proyectos.getId());
        this.setIdAlumno(proyectos.getIdAlumno());
        this.setDocumento(proyectos.getDocumento());
        this.setPresentacion(proyectos.getPresentacion());
        this.setNotaDoc(proyectos.getNotaDoc());
        this.setNotaPres(proyectos.getNotaPres());
        this.setNotaFinal(proyectos.getNotaFinal());
        this.setExposicion(proyectos.getExposicion());
        this.setTutoria1(proyectos.getTutoria1());
        this.setTutoria2(proyectos.getTutoria2());
        this.setTutoria3(proyectos.getTutoria3());
        this.setEstadoTutoria1(proyectos.getEstadoTutoria1());
        this.setEstadoTutoria2(proyectos.getEstadoTutoria2());
        this.setEstadoTutoria3(proyectos.getEstadoTutoria3());
        return this;
    }

}
