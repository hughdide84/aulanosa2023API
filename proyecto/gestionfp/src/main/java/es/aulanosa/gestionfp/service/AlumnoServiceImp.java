package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImp implements AlumnoService{


    @Autowired
    private AlumnoRepository repositorio;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> buscarTodo() {
        return repositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno buscarPorId(int id) {
        return repositorio.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
    @Transactional
    @Override
    public Alumno guardar(Alumno alumno) {
        return repositorio.save(alumno);
    }
    @Transactional
    @Override
    public Alumno modificar(Alumno alumno) throws NoSeHaEncontradoException {

        Optional<Alumno> alumnoOptional = repositorio.findById(2);
        if(alumnoOptional.isPresent()){
            alumnoOptional.get().setNombre("Paco");
            Alumno alumnosExternos1 = repositorio.save(alumnoOptional.get());

            System.out.println("Alumno modificado a: " + alumnosExternos1);
        }else{
            throw new NoSeHaEncontradoException("No se ha encontrado el alumno especificado");
        }
    }
}
