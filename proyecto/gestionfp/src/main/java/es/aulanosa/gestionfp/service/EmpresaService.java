package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.Empresa;

import java.util.List;
import java.util.Map;

public interface EmpresaService {

    Empresa findById(int id);
    Empresa save(Empresa empresa);
    Empresa update(Empresa empresa) throws NoSeHaEncontradoException;
    void deleteById(int id);
    List<Empresa> findAll();
    List<Empresa> findAllByNombre(String nombre);

    Map<Empresa, List<Alumno>> buscarEmpresaConAlumnosPorCursoEstudio(int idCurso, int idEstudios);
}
