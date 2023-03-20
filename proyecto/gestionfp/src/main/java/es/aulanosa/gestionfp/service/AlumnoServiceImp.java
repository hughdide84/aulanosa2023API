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


    //instanciar repositorio para usar sus metodos
    @Autowired
    private AlumnoRepository repositorio;

    //metodo que devuelve una lista de todos los alumnos
    @Override
    @Transactional(readOnly = true)
    public List<Alumno> buscarTodo() {
        return repositorio.findAll();
    }

    //metodo que devuelve toda la informacion de el alumno que se le pase el id
    @Override
    @Transactional(readOnly = true)
    public Optional<Alumno> buscarPorId(int id) {

        return repositorio.findById(id);
    }
    //metodo para eliminar el alumno pasando su id
    @Transactional
    @Override
    public void eliminarAlumno(int id) throws NoSeHaEncontradoException {
        if(repositorio.findById(id).isPresent()){
            repositorio.deleteById(id);
        }else {
            throw new NoSeHaEncontradoException("No existe en alumno");
        }

    }
    //metodo para insertar un alumno en la BD
    @Transactional
    @Override
    public Alumno guardarAlumno(Alumno alumno) {

        return repositorio.save(alumno);
    }
    //metodo para modificar los atributos de un alumno a partir de su id
    @Transactional
    @Override
    public Alumno modificarAlumno(Alumno alumno) throws NoSeHaEncontradoException {

        if(repositorio.existsById(alumno.getId())){
            repositorio.save(alumno);

        }else{
            throw new NoSeHaEncontradoException("No se ha encontrado el alumno especificado");
        }
        return alumno;
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno buscarPorUsuario(String nombre) {
        return repositorio.findByUsuario(nombre);
    }
}
