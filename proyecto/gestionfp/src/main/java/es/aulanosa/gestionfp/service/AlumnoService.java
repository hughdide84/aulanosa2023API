package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.Curso;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AlumnoService {

    //definicion de metodos CRUD para Alumno
    Alumno guardarAlumno(Alumno alumno);
    List<Alumno> buscarTodo();
    Optional<Alumno> buscarPorId(int id);
    Alumno modificarAlumno(Alumno alumno) throws NoSeHaEncontradoException;
    void eliminarAlumno(int id) throws NoSeHaEncontradoException;


    Alumno buscarPorUsuario(String nombre);

    List<Alumno> buscarPorEstado() throws NoSeHaEncontradoException;
    List<AlumnoEmpresa> buscarPorCursoEstudioEstado(int idCurso, int idEstudios);

}
