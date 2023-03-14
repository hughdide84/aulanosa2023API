package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;

import java.util.List;

public interface AlumnoService {

    Alumno guardar(Alumno alumno);
    List<Alumno> buscarTodo();

    Object buscarPorId(int id);
    Alumno modificar(Alumno alumno) throws NoSeHaEncontradoException;
    void eliminar(int id);


}
