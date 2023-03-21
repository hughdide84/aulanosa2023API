package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.AsignaturaHorario;
import es.aulanosa.gestionfp.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AsignaturaHorarioDTO {

    private int id;

    private int idAsignatura;

    private char dia;

    private Time inicio;

    private Time fin;


    public AsignaturaHorario convertirModel() {
        AsignaturaHorario asignaturaHorario = new AsignaturaHorario();

        asignaturaHorario.setId(this.id);
        asignaturaHorario.setIdAsignatura(this.idAsignatura);
        asignaturaHorario.setDia(this.dia);
        asignaturaHorario.setInicio(this.inicio);
        asignaturaHorario.setFin(this.fin);

        return asignaturaHorario;
    }

    public AsignaturaHorarioDTO convertirDTO(AsignaturaHorario asignaturaHorario) {
        this.setId(asignaturaHorario.getId());
        this.setIdAsignatura(asignaturaHorario.getIdAsignatura());
        this.setDia(asignaturaHorario.getDia());
        this.setInicio(asignaturaHorario.getInicio());
        this.setFin(asignaturaHorario.getFin());

        return this;
    }



}
