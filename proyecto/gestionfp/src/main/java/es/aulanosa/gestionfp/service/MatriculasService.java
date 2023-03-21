package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Matricula;

import java.util.List;
import java.util.Optional;

public interface MatriculasService {
    public Matricula insertarMatricula(Matricula matricula);

    public Optional<Matricula> consultarPorIdMatricula(Integer id);

    public List<Matricula> consultarTodasMatriculas();

    public Matricula modificarMatricula(Matricula matricula) throws NoSuchFieldException;

    public void eliminarMatricula(Integer id);

    List<Matricula> buscarPorNombreDeMatricula(String nombre);
    List<Matricula> buscarPorMesDeMatricula(Integer numMes);

    List<Matricula> buscarPorMesDeMatricula(String nombre);

    public List<Curso> buscarTodosCursosPorId (Integer id);
}
