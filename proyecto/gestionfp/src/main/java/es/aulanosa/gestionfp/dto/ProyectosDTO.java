package es.aulanosa.gestionfp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyectosDTO {

    private int id;

    private int idAlumno;

    private String documento;

    private String presentacion;

    private int notaDoc;

    private int notaPres;

    private int notaFinal;

    







}
