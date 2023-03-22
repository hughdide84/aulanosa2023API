package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Proyectos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Proyectos toModel() {
        Proyectos proyectos = new Proyectos();

        proyectos.setId(this.id);
        proyectos.setIdAlumno(this.idAlumno);
        proyectos.setDocumento(this.documento);
        proyectos.setPresentacion(this.presentacion);
        proyectos.setNotaDoc(this.notaDoc);
        proyectos.setNotaPres(this.notaPres);
        proyectos.setNotaFinal(this.notaFinal);

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

        return this;
    }

}
