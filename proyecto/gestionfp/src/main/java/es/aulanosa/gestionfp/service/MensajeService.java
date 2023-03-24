package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Estudios;
import es.aulanosa.gestionfp.model.Mensaje;

import java.util.List;
import java.util.Optional;

public interface MensajeService {

    public Mensaje insertarMensaje(Mensaje mensaje);

    public Optional<Mensaje> consultarPorIdMensaje(Integer id);

    public List<Mensaje> consultarTodosMensajes();

    public Mensaje modificarMensaje(Mensaje mensaje) throws NoSuchFieldException;

    public void eliminarMensaje(Integer id);




}