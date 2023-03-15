package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AsignaturaHorario;

import java.util.List;
import java.util.Optional;

public interface AsignaturaHorarioService {


    Optional<AsignaturaHorario> buscarPorId(Integer id) throws NoSeHaEncontradoException;
    AsignaturaHorario insertar(AsignaturaHorario asignaturaHorario);
    AsignaturaHorario modificar(AsignaturaHorario asignaturaHorario) throws NoSeHaEncontradoException;

    List<AsignaturaHorario> buscarPorCurso(int idAsignatura) throws NoSeHaEncontradoException;
    void eliminar(int id) throws NoSeHaEncontradoException;

}