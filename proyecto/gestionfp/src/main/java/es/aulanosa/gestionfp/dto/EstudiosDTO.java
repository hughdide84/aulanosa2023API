package es.aulanosa.gestionfp.dto;


import es.aulanosa.gestionfp.model.Estudios;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Clase que representa el objeto de transferencia de datos de la entidad Estudios
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudiosDTO {

    private Integer id;

    private String nombre;

    private boolean fct;

    private boolean pext;

    public EstudiosDTO(String nombre, boolean fct, boolean pext) {
        this.nombre = nombre;
        this.fct = fct;
        this.pext = pext;
    }

    public Estudios convertirModel(){
        Estudios estudios = new Estudios();
        estudios.setId(this.id);
        estudios.setNombre(this.nombre);
        estudios.setFct(this.fct);
        estudios.setPext(this.pext);
        return estudios;
    }

    public EstudiosDTO crearDTO(Estudios estudios){
        this.setId(estudios.getId());
        this.setNombre(estudios.getNombre());
        this.setFct(estudios.isFct());
        this.setPext(estudios.isPext());
        return this;
    }
}
