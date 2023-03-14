package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Proyectos;

import java.util.List;
import java.util.Optional;

//Interface por la que pasamos los metodos que utilizara el ServiceImp
public interface ProyectosService {
    public Proyectos save(Proyectos proyectos);
    public List<Proyectos> findAll();
    public Optional<Proyectos> findById(Integer id);
    public Proyectos update(Proyectos proyectos) throws NoSeHaEncontradoException;
    public void deleteById(Integer id);
}
