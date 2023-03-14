package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnoExterno;

import java.util.List;
import java.util.Optional;

public interface AlumnoExternoService {

    List<AlumnoExterno> listarTodo();


    public void eliminar(Integer id);
    public AlumnoExterno guardar(AlumnoExterno alumnoExterno);
    public AlumnoExterno modificar(AlumnoExterno alumnoExterno) throws NoSeHaEncontradoException;
    public Optional<AlumnoExterno> listarPorId(Integer id);

}