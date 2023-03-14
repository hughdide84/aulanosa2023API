package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Proyectos;
import es.aulanosa.gestionfp.repository.ProyectosRepository;
import org.springframework.transaction.annotation.Transactional;
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
    public Proyectos save(Proyectos proyectos) {
        return repository.save(proyectos);
    }

    //Metodo de listado completo de la tabla proyectos completa
    @Override
    @Transactional(readOnly = true)
    public List<Proyectos> findAll() {
        return repository.findAll();
    }

    //Metodo de busqueda de campos de la tabla proyectos por id
    @Override
    @Transactional(readOnly = true)
    public Optional<Proyectos> findById(Integer id) {
        return repository.findById(id);
    }

    //Metodo para modificar datos de la tabla proyectos
    @Override
    public Proyectos update(Proyectos proyectos) throws NoSeHaEncontradoException {
        if (repository.findById(proyectos.getId()) == null) {
            return repository.save(proyectos);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el usuario");
        }
    }

    //Metodo para borrar datos de la tabla proyectos
    @Override
    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
