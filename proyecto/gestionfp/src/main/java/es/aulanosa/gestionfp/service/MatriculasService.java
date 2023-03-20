package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Matricula;

import java.util.List;
import java.util.Optional;

public interface MatriculasService {
    public Matricula insertar(Matricula matricula);

    public Optional<Matricula> consultarPorId(Integer id);

    public List<Matricula> consultarTodos();

    public Matricula modificar(Matricula matricula) throws NoSuchFieldException;

    public void eliminar(Integer id);

    List<Matricula> buscarPorNombreDeMatricula(String nombre);
    List<Matricula> buscarPorMesDeMatricula(Integer numMes);

    public List<Matricula> buscarTodosCursosPorId (Integer id);
}
