package es.aulanosa.gestionfp.dto;

import es.aulanosa.gestionfp.model.Empresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {
    private Integer id;
    private Integer idCurso;
    private Integer idEstudios;
    private String nombre;
    private String direccionSocial;
    private String direccionTrabajo;
    private String cif;
    private String representante;
    private String contacto;
    private String tutor1;
    private String tutor2;
    private String tutor3;
    private char convenio;
    private char planIndividual;
    private char hojaActividades;

    public Empresa convertirModel(){
        Empresa empresa = new Empresa();
        empresa.setId(this.id);
        empresa.setIdCurso(this.idCurso);
        empresa.setIdEstudios(this.idEstudios);
        empresa.setNombre(this.nombre);
        empresa.setDireccionSocial(this.direccionSocial);
        empresa.setDireccionTrabajo(this.direccionTrabajo);
        empresa.setCif(this.cif);
        empresa.setRepresentante(this.representante);
        empresa.setContacto(this.contacto);
        empresa.setTutor1(this.tutor1);
        empresa.setTutor2(this.tutor2);
        empresa.setTutor3(this.tutor3);
        empresa.setConvenio(this.convenio);
        empresa.setPlanIndividual(this.planIndividual);
        empresa.setHojaActividades(this.hojaActividades);
        return empresa;
    }

    public EmpresaDTO crearDTO(Empresa empresa) {
        this.setId(empresa.getId());
        this.setIdCurso(empresa.getIdCurso());
        this.setIdEstudios(empresa.getIdEstudios());
        this.setNombre(empresa.getNombre());
        this.setDireccionSocial(empresa.getDireccionSocial());
        this.setDireccionTrabajo(empresa.getDireccionTrabajo());
        this.setCif(empresa.getCif());
        this.setRepresentante(empresa.getRepresentante());
        this.setContacto(empresa.getContacto());
        this.setTutor1(empresa.getTutor1());
        this.setTutor2(empresa.getTutor2());
        this.setTutor3(empresa.getTutor3());
        this.setConvenio(empresa.getConvenio());
        this.setPlanIndividual(empresa.getPlanIndividual());
        this.setHojaActividades(empresa.getHojaActividades());
        return this;
    }
}
