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
    //lista todos los alumnos
    public List<AlumnoExterno> listarTodo() {
        return repository.findAll();
    }

    @Override
    @Transactional
    //elimina
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    //guarda
    public AlumnoExterno guardar(AlumnoExterno alumnoExterno) {
        return repository.save(alumnoExterno);
    }

    @Override
    @Transactional
    //modifica
    public AlumnoExterno modificar(AlumnoExterno alumnoExterno) throws NoSeHaEncontradoException {

        //comprueba que el id elegido existe,en caso de que si, entra en el bucle y modifica el campo de ese id
        if(repository.existsById(alumnoExterno.getId())){
            return repository.save(alumnoExterno);
        }else{
            throw new NoSeHaEncontradoException("No se ha encontrado el alumno externo");
        }
    }

    @Override
    @Transactional(readOnly = true)
    //lista segun la id que se le pasa
    public Optional<AlumnoExterno> listarPorId(Integer id) {
        return repository.findById(id);
    }
}