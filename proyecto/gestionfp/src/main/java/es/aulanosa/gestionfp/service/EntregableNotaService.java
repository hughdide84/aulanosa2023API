package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.EntregableNota;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EntregableNotaService {

    EntregableNota insertarEntregablesNotas (EntregableNota entregableNotas);

    @Transactional(readOnly = true)
    //Consultar EntregableNotas por id
    Optional<EntregableNota> buscarEntregablesNotasPorId(Integer id);

    EntregableNota modificarEntregablesNotas (EntregableNota entregableNotas) throws NoSeHaEncontradoException;

    void eliminarEntregablesNotas (EntregableNota entregableNotas);

    List<EntregableNota> buscarEntregablesNotas ();

}
