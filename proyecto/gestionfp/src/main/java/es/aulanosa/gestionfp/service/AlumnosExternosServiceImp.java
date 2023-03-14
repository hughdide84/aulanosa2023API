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
public class AlumnosExternosServiceImp implements AlumnosExternosService {
    @Autowired
    private AlumnoExternoRepository repository;


    @Override
    @Transactional(readOnly = true)
    public List<AlumnoExterno> listarTodo() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public AlumnoExterno guardar(AlumnoExterno alumnoExterno) {
        return repository.save(alumnoExterno);
    }

    @Override
    @Transactional
    public AlumnoExterno modificar(AlumnoExterno alumnoExterno) throws NoSeHaEncontradoException {
        if(repository.existsById(alumnoExterno.getId())){
            return repository.save(alumnoExterno);
        }else{
            throw new NoSeHaEncontradoException("No se ha encontrado el alumno externo");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlumnoExterno> listarPorId(Integer id) {
        return repository.findById(id);
    }
}