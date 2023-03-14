package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Proyectos;
import es.aulanosa.gestionfp.repository.ProyectosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectosServiceImp implements ProyectosService{

    @Autowired
    private ProyectosRepository repository;

    //Metodo para insertar datos en la tabla proyectos
    @Override
    @Transactional
    public Proyectos guardar(Proyectos proyectos) {
        return repository.save(proyectos);
    }

    //Metodo de listado completo de la tabla proyectos completa
    @Override
    @Transactional
    public List<Proyectos> buscarTodo() {
        return repository.findAll();
    }

    //Metodo de busqueda de campos de la tabla proyectos por id
    @Override
    @Transactional
    public Optional<Proyectos> consultar(Integer id) {
        return repository.findById(id);
    }

    //Metodo para modificar datos de la tabla proyectos
    @Override
    public Proyectos update(Proyectos proyectos) throws NoSeHaEncontradoException {
        var a = repository.findById(proyectos.getId());
        if (!a.isEmpty()) {
            return repository.save(proyectos);
        } else {
            throw new NoSeHaEncontradoException("No se ha el campo que desea modificar");
        }
    }

    //Metodo para borrar datos de la tabla proyectos
    @Override
    @Transactional
    public void borrar(Integer id) {
        repository.deleteById(id);
    }
}
