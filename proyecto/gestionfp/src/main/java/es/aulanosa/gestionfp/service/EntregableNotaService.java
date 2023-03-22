package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.EntregableNota;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EntregableNotaService {

    EntregableNota insertarEntregableNota (EntregableNota entregableNota);

    @Transactional(readOnly = true)
    //Consultar EntregableNotas por id
    Optional<EntregableNota> buscarEntregableNotaPorId(Integer id);

    EntregableNota modificarEntregableNota (EntregableNota entregableNota) throws NoSeHaEncontradoException;

    void eliminarEntregableNota (EntregableNota entregableNota);

    List<EntregableNota> buscarEntregableNota ();

}
