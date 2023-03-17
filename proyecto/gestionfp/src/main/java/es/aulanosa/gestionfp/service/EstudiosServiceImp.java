package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Estudios;
import es.aulanosa.gestionfp.repository.EstudiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional(readOnly = true)
    //Metodo para consultar un estudio por su id
    public Optional<Estudios> consultarPorId(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    //Metodo para consultar todos los estudios
    public List<Estudios> consultarTodos() {
        return repositorio.findAll();
    }

    @Override
    @Transactional
    //Metodo para modificar un estudio
    public Estudios modificar(Estudios estudios) throws NoSuchFieldException{
        if (repositorio.findById(estudios.getId()).isPresent()){
            return repositorio.save(estudios);
        }
        else{
            throw new NoSuchFieldException("No existe el estudio con id: " + estudios.getId());
        }
    }

    @Override
    @Transactional
    //Metodo para eliminar un estudio por su id
    public void eliminar(Integer id) {
        repositorio.deleteById(id);
    }


}
