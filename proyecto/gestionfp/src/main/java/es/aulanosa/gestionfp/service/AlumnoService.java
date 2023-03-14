package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    //definicion de metodos CRUD para Alumno
    Alumno guardarAlumno(Alumno alumno);
    List<Alumno> buscarTodo();

    Optional<Alumno> buscarPorId(int id);
    Alumno modificarAlumno(Alumno alumno) throws NoSeHaEncontradoException;
    void eliminarAlumno(int id) throws NoSeHaEncontradoException;


}
