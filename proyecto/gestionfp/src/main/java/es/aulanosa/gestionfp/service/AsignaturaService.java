package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Asignatura;

import java.util.List;

public interface AsignaturaService {

    List<Asignatura> findAll();
    List<Asignatura> findAllByNombre(String nombre);
    Asignatura findById(int id);
    Asignatura save(Asignatura asignatura);
    void deleteById(int id);
    Asignatura update(Asignatura asignatura);

}
