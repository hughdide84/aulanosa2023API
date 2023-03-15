package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Asignatura;

import java.util.List;

public interface AsignaturaService {

    List<Asignatura> buscarTodo();
    List<Asignatura> buscarTodoPorNombreCon(String nombre);
    Asignatura buscarPorId(int id);
    Asignatura guardar(Asignatura asignatura);
    void borrarPorId(int id);
    Asignatura modificar(Asignatura asignatura);

}
