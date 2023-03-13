package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Estudios;
import es.aulanosa.gestionfp.repository.EstudiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EstudiosServiceImp implements EstudiosService {

    @Autowired
    private EstudiosRepository repositorio;

    @Override
    @Transactional
    public Optional<Estudios> consultarPorId(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    @Transactional
    public void borrar(Estudios estudios) {
        repositorio.delete(estudios);
    }

    @Override
    @Transactional
    public Estudios insertar(Estudios estudios) {
        return repositorio.save(estudios);
    }

}
