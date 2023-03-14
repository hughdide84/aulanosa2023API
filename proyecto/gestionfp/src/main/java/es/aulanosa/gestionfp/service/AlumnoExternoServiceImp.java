package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnoExterno;
import es.aulanosa.gestionfp.repository.AlumnoExternoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoExternoServiceImp implements AlumnoExternoService {
    @Autowired
    private AlumnoExternoRepository repository;


    @Override
    @Transactional(readOnly = true)
    //lista todos los campos
    public List<AlumnoExterno> listarTodo() {
        return repository.findAll();
    }

    @Override
    @Transactional
    //elimina el alumno externo que tenga el id que se le pasa
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    //guarda el alumno externo que se le pasa
    public AlumnoExterno guardar(AlumnoExterno alumnoExterno) {
        return repository.save(alumnoExterno);
    }

    @Override
    @Transactional
    //modifica el alumno que se le pasa
    public AlumnoExterno modificar(AlumnoExterno alumnoExterno) throws NoSeHaEncontradoException {
        //comprueba que existe el id que se le ha pasado, y si existe se modifica el campo
        if(repository.existsById(alumnoExterno.getId())){
            return repository.save(alumnoExterno);
        }else{
            throw new NoSeHaEncontradoException("No se ha encontrado el alumno externo");
        }
    }

    @Override
    @Transactional(readOnly = true)
    //lista seguna la id que se le pasa
    public Optional<AlumnoExterno> listarPorId(Integer id) {
        return repository.findById(id);
    }
}