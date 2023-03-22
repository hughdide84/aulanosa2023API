package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase que implementa los metodos definidos en la interfaz CursoService
 */
@Service
public class CursoServiceImp implements CursoService {


    //llamar al repositorio e implementar metodos
    @Autowired
    private CursoRepository repositorio;
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Curso> buscarTodo() {
        return repositorio.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Curso buscarPorId(int id) {
        return repositorio.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void eliminarCurso(int id) throws NoSeHaEncontradoException {

        if(repositorio.findById(id).isPresent()){
            repositorio.deleteById(id);
        }else{
            throw new NoSeHaEncontradoException("Curso no existe");
        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Curso insertarCurso(Curso curso) {
        return repositorio.save(curso);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Curso modificarCurso(Curso curso) throws NoSeHaEncontradoException {
        if (repositorio.findById(curso.getId()).isPresent()) {
            return repositorio.save(curso);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el usuario");
        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    //Metodo para consultar estados activos
    public List<Curso> buscarTodoPorEstadoActivo() {
        return repositorio.buscarTodoPorEstadoActivo();
    }


}
