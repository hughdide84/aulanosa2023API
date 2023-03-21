package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Entregable;
import es.aulanosa.gestionfp.model.Matricula;

import java.util.List;
import java.util.Optional;

public interface EntregableService {

    public Entregable insertarEntregable(Entregable entregable);

    public Optional<Entregable> consultarPorIdEntregable(Integer id);

    public List<Entregable> consultarTodosEntregables();

    public Entregable modificarEntregable(Entregable entregable) throws NoSuchFieldException;

    public void eliminarEnregable(Integer id);


}
