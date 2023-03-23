package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Empresa;

import java.util.List;

public interface EmpresaService {

    Empresa findById(int id);
    Empresa save(Empresa empresa);
    Empresa update(Empresa empresa) throws NoSeHaEncontradoException;
    void deleteById(int id);
    List<Empresa> findAll();
    List<Empresa> findAllByNombre(String nombre);

    List<Empresa> findByIdCursoAndIdEstudio(int idCurso, int idEstudio);
}
