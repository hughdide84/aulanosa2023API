package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Estudios;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EstudiosService {

    public Estudios insertar(Estudios estudios);

    public Optional <Estudios> consultarPorId(Integer id);

    public List<Estudios> consultarTodos();

    public Estudios modificar(Estudios estudios) throws NoSuchFieldException;

    public void eliminar(Integer id);


}
