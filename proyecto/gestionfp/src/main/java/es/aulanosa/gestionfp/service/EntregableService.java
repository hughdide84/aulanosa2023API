package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Entregable;

import java.util.List;
import java.util.Optional;

public interface EntregableService {

    public Entregable insertarEntregable(Entregable entregable) throws NoSeHaEncontradoException;

    public Optional<Entregable> consultarPorIdEntregable(Integer id);

    public List<Entregable> consultarTodosEntregables();

    public Entregable modificarEntregable(Entregable entregable) throws NoSuchFieldException;

    public void eliminarEntregable(Integer id);


}
