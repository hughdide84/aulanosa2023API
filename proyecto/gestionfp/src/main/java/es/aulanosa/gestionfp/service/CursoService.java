package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Curso;

import java.util.List;

//definir metodos a implementar
public interface CursoService {

    List<Curso> buscarTodo();

     Curso buscarPorId(int id);
     void eliminar(int id);
     Curso guardar(Curso curso);
     Curso modificar(Curso curso);







}
