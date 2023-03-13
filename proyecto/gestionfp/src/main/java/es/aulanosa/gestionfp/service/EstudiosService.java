package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Estudios;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EstudiosService {

    public Estudios insertar(Estudios estudios);

    public Optional <Estudios> consultarPorId(Integer id);

    public void eliminar(Integer id);


}
