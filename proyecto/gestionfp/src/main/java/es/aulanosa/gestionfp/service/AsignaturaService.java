package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Asignatura;

import java.util.List;

public interface AsignaturaService {

    List<Asignatura> buscarTodoAsignatura();
    List<Asignatura> buscarTodoPorNombreAsignaturaConteniendoNombreAsignatura(String nombre);
    Asignatura buscarPorIdAsignatura(int id);
    Asignatura guardarAsignatura(Asignatura asignatura);
    void borrarPorIdAsignatura(int id);
    Asignatura modificarAsignatura(Asignatura asignatura);

}
