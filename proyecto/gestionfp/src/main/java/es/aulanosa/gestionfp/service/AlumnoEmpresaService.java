package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.Empresa;

import java.util.List;

public interface AlumnoEmpresaService {

    List<AlumnoEmpresa> findAll();
    AlumnoEmpresa findById(int id);
    AlumnoEmpresa save(AlumnoEmpresa alumnosEmpresas);
    void deleteById(int id);
    AlumnoEmpresa update (AlumnoEmpresa alumnosEmpresas) throws NoSeHaEncontradoException;

    List<Alumno> findAllAlumnoByEmpresaId(int EmpresaId);
    List<Empresa> findAllEmpresaByAlumnoId(int AlumnoId);


}
