package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Alumno;

import java.util.List;

public interface AlumnoService {

    List<Alumno> buscarTodo();

    Alumno buscarPorId(int id);
    void eliminar(int id);
    Alumno guardar(Alumno alumno);
    Alumno modificar(Alumno alumno);

}
