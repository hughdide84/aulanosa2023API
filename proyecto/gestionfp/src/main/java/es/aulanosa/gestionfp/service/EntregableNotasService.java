package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.EntregableNotas;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EntregableNotasService {

    EntregableNotas insertarEntregablesNotas (EntregableNotas entregableNotas);

    @Transactional(readOnly = true)
    //Consultar EntregableNotas por id
    Optional<EntregableNotas> buscarEntregablesNotasPorId(Integer id);

    EntregableNotas modificarEntregablesNotas (EntregableNotas entregableNotas) throws NoSeHaEncontradoException;

    void eliminarEntregablesNotas (EntregableNotas entregableNotas);

    List<EntregableNotas> buscarEntregablesNotas ();

}
