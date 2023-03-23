package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Proyectos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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

        return this;
    }

}
