package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnoExterno;

import java.util.List;
import java.util.Optional;

public interface AlumnoExternoService {

    List<AlumnoExterno> listarTodo();


    public void eliminar(Integer id) throws NoSeHaEncontradoException;
    public AlumnoExterno guardar(AlumnoExterno alumnoExterno);
    public AlumnoExterno modificar(AlumnoExterno alumnoExterno) throws NoSeHaEncontradoException;
    public Optional<AlumnoExterno> listarPorId(Integer id) throws NoSeHaEncontradoException;
    List<AlumnoExterno> buscarPorEstado() throws NoSeHaEncontradoException;
    List<AlumnoExterno> buscarPorNombreConteniendo(String nombre);
    AlumnoExterno buscarPorNombreEs(String nombre);
    List<AlumnoExterno> buscarPorEmailConteniendo(String email);
    AlumnoExterno buscarPorEmailEs(String email);
    AlumnoExterno buscarPorTelefono(String telefono);
    AlumnoExterno buscarPorUniversidadEs(String universidad);
    List<AlumnoExterno> buscarPorUniversidadConteniendo(String universidad);
    List<AlumnoExterno> buscarPorTitulacionConteniendo(String titulacion);
    AlumnoExterno buscarPorTitulacionEs(String titulacion);
    List<AlumnoExterno> buscarPorEspecialidadConteniendo(String especialidad);
    AlumnoExterno buscarPorEspecialidadEs(String especialidad);

}