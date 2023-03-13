package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Estudios;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EstudiosService {


    public Optional <Estudios> consultarPorId(Integer id);

    public void borrar(Estudios estudios);

    public Estudios insertar(Estudios estudios);



}
