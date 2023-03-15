package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Proyectos;

import java.util.List;
import java.util.Optional;

//Interface por la que pasamos los metodos que utilizara el ServiceImp
public interface ProyectosService {
    public Proyectos guardar(Proyectos proyectos);
    public List<Proyectos> buscarTodo();
    public Proyectos buscarPorId(Integer id);
    public Proyectos modificar(Proyectos proyectos) throws NoSeHaEncontradoException;
    public void borrar(Integer id);
}
