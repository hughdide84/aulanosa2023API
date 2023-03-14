package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    Alumno guardar(Alumno alumno);
    List<Alumno> buscarTodo();

    Optional<Alumno> buscarPorId(int id);
    Alumno modificar(Alumno alumno) throws NoSeHaEncontradoException;
    void eliminar(int id);


}
