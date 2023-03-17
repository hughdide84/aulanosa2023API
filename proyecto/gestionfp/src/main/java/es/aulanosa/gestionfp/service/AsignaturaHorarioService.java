package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AsignaturaHorario;

import java.util.List;
import java.util.Optional;

public interface AsignaturaHorarioService {


    Optional<AsignaturaHorario> buscarPorIdAsignaturaHorario(Integer id) throws NoSeHaEncontradoException;
    AsignaturaHorario insertarAsignaturaHorario(AsignaturaHorario asignaturaHorario);
    AsignaturaHorario modificarAsignaturaHorario(AsignaturaHorario asignaturaHorario) throws NoSeHaEncontradoException;

    List<AsignaturaHorario> buscarPorCursoAsignaturaHorario(int idAsignatura, int idCurso, int nivel) throws NoSeHaEncontradoException;
    void eliminarAsignaturaHorario(int id) throws NoSeHaEncontradoException;

    List<AsignaturaHorario> listarTodoAsignaturaHorario();

    List<AsignaturaHorario> listarHorariosSegunCursoEstudioNivel(int idCurso, int idEstudio, int nivel);

}