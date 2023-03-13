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
    //Metodo para insertar un estudio
    public Estudios insertar(Estudios estudios) {
        return repositorio.save(estudios);
    }

    @Override
    @Transactional
    //Metodo para consultar un estudio por su id
    public Optional<Estudios> consultarPorId(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    @Transactional
    //Metodo para eliminar un estudio por su id
    public void eliminar(Integer id) {
        repositorio.deleteById(id);
    }

}
