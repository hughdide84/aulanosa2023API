package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.EntregableNotas;

import java.util.List;

public interface EntregableNotasService {

    EntregableNotas insertarEntregablesNotas (EntregableNotas entregableNotas);

    EntregableNotas buscarEntregablesNotasPorId (int id);

    EntregableNotas modificarEntregablesNotas (EntregableNotas entregableNotas) throws NoSeHaEncontradoException;

    void eliminarEntregablesNotas (EntregableNotas entregableNotas);

    List<EntregableNotas> buscarEntregablesNotas (EntregableNotas entregableNotas);

}
