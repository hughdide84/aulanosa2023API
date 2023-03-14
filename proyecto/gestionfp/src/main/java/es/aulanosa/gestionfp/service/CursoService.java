package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Curso;

import java.util.List;

//definir metodos a implementar
public interface CursoService {

    Curso insertarCurso(Curso curso);
    List<Curso> buscarTodo();
     Curso buscarPorId(int id);
    Curso modificarCurso(Curso curso) throws NoSeHaEncontradoException;
     void eliminarCurso(int id) throws NoSeHaEncontradoException;








}
