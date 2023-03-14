package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnosEmpresas;

import java.util.List;

public interface AlumnosEmpresasService {

    List<AlumnosEmpresas> findAll();
    AlumnosEmpresas findById(int id);
    AlumnosEmpresas save(AlumnosEmpresas alumnosEmpresas);
    void deleteById(int id);
    AlumnosEmpresas update (AlumnosEmpresas alumnosEmpresas) throws NoSeHaEncontradoException;
}
