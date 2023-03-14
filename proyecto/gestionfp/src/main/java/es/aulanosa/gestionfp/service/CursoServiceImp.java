package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CursoServiceImp implements CursoService {


    //llamar al repositorio e implementar metodos
    @Autowired
    private CursoRepository repositorio;

    //devuelve una lista de todos los cursos
    @Override
    @Transactional(readOnly = true)
    public List<Curso> buscarTodo() {
        return repositorio.findAll();
    }

    //devuelve el curso con el id que se le pase
    @Override
    @Transactional(readOnly = true)
    public Curso buscarPorId(int id) {
        return repositorio.findById(id).orElse(null);
    }

    //elimina el curso con el id que se le pase
    @Override
    @Transactional
    public void eliminarCurso(int id) {
         repositorio.deleteById(id);
        System.out.println("Curso eliminado con exito");
    }
    //inserta en la BD un objeto curso con los atributos que se le pase
    @Override
    @Transactional
    public Curso insertarCurso(Curso curso) {
        return repositorio.save(curso);
    }

    //modifica atributos de un objeto curso y lo sobreescribe
    @Override
    @Transactional
    public Curso modificarCurso(Curso curso) throws NoSeHaEncontradoException {
        if (repositorio.findById(curso.getId()).isPresent()) {
            return repositorio.save(curso);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el usuario");
        }

    }


}
