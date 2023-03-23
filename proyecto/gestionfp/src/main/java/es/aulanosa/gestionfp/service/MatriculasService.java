package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Matricula;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los métodos que se van a implementar en la clase
 */
public interface MatriculasService {
    /**
     * Método que inserta una matricula
     *
     * @param matricula
     * @return nueva matricula
     */
    public Matricula insertarMatricula(Matricula matricula);

    /**
     * Método que consulta una matricula por su id
     *
     * @param id
     * @return matricula con ese id
     */
    public Optional<Matricula> consultarPorIdMatricula(Integer id);

    /**
     * Método que consulta todas las matriculas
     *
     * @return lista de matriculas
     */
    public List<Matricula> consultarTodasMatriculas();

    /**
     * Método que modifica una matricula
     *
     * @param matricula
     * @return matricula modificada
     * @throws NoSuchFieldException
     */
    public Matricula modificarMatricula(Matricula matricula) throws NoSuchFieldException;

    /**
     * Método que elimina una matricula por su id
     *
     * @param id
     * @return matricula eliminada
     */
    public void eliminarMatricula(Integer id);

    /**
     * Método que busca una matricula por su nombre
     *
     * @param nombre
     * @return lista de matriculas con ese nombre
     */
    List<Matricula> buscarPorNombreDeMatricula(String nombre);

    /**
     * Método que busca una matricula por su mes
     *
     * @param numMes
     * @return lista de matriculas con ese mes
     */
    List<Matricula> buscarPorMesDeMatricula(Integer numMes);

    /**
     * Método que busca una matricula por su año
     *
     * @param id
     * @return lista de matriculas con ese año
     */
    public List<Matricula> buscarTodosCursosPorId(Integer id);
}
