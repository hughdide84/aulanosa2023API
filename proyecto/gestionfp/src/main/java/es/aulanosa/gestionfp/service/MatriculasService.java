package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Matriculas;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MatriculasService {
    public Matriculas insertar(Matriculas matriculas);

    public Optional<Matriculas> consultarPorId(Integer id);

    public List<Matriculas> consultarTodos();

    public Matriculas modificar(Matriculas matriculas) throws NoSuchFieldException;

    public void eliminar(Integer id);

    List<Matriculas> buscarPorNombreDeMatricula(String nombre);

    public List<Curso> buscarTodosCursosPorId (Integer id);
}
