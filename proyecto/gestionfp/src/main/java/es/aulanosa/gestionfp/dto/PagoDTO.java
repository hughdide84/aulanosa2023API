package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Pago;
import es.aulanosa.gestionfp.model.Proyectos;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {

    private int id;
    private int idMatricula;
    private String recibo;
    private float pago;
    private char estado;
    private String observaciones;
    private int idUsuario;
    private Timestamp fecha;


    public Pago convertirModel() {
        Pago pago = new Pago();

        pago.setId(this.id);
        pago.setPago(this.getPago());
        pago.setEstado(this.getEstado());
        pago.setFecha(this.getFecha());
        pago.setRecibo(this.getRecibo());
        pago.setIdMatricula(this.getIdMatricula());
        pago.setIdUsuario(this.getIdUsuario());
        pago.setObservaciones(this.getObservaciones());


        return pago;
    }

    public PagoDTO convertirDTO(Pago pago) {
        this.setId(pago.getId());
        this.setPago(pago.getPago());
        this.setEstado(pago.getEstado());
        this.setFecha(pago.getFecha());
        this.setRecibo(pago.getRecibo());
        this.setIdMatricula(pago.getIdMatricula());
        this.setIdUsuario(getIdUsuario());
        this.setObservaciones(pago.getObservaciones());

        return this;
    }

}
