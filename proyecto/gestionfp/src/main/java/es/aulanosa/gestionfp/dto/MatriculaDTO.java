package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Matricula;
import es.aulanosa.gestionfp.model.Mensaje;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDTO {

    private Integer id;

    private String factura;

    private String nombre;

    private String nif;

    private float cuota;

    private float matricula;

    private Integer idCurso;

    private String observaciones;

    private Timestamp fechaBaja;

    private Integer idUsuario;

    private Timestamp fecha;

    private float saldo;

    private float totalPagos;

    public Matricula toModel(){

        Matricula matricula = new Matricula();

        matricula.setId(this.id);
        matricula.setFactura(this.factura);
        matricula.setNombre(this.nombre);
        matricula.setNif(this.nif);
        matricula.setCuota(this.cuota);
        matricula.setMatricula(this.matricula);
        matricula.setIdCurso(this.idCurso);
        matricula.setFechaBaja(this.fechaBaja);
        matricula.setObservaciones(this.observaciones);
        matricula.setIdUsuario(this.idUsuario);
        matricula.setFecha(this.fecha);
        matricula.setSaldo(this.getSaldo());
        matricula.setTotalPagos(this.getTotalPagos());

        return matricula;

    }

    public MatriculaDTO toDTO(Matricula matricula){

        this.setId(matricula.getId());
        this.setFactura(matricula.getFactura());
        this.setNombre(matricula.getNombre());
        this.setNif(matricula.getNif());
        this.setCuota(matricula.getCuota());
        this.setMatricula(matricula.getMatricula());
        this.setIdCurso(matricula.getIdCurso());
        this.setFechaBaja(matricula.getFechaBaja());
        this.setObservaciones(matricula.getObservaciones());
        this.setIdUsuario(matricula.getIdUsuario());
        this.setFecha(matricula.getFecha());
        this.setSaldo(matricula.getSaldo());
        this.setTotalPagos(matricula.getTotalPagos());

        return this;
    }

}
