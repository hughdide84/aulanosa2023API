package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Entregable;
import es.aulanosa.gestionfp.repository.EntregableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EntregableServiceImp implements EntregableService{

    @Autowired
    private EntregableRepository repositorio;

    @Override
    @Transactional
    //insertar un entregable en la BD
    public Entregable insertarEntregable(Entregable entregable) {
        return repositorio.save(entregable);
    }

    @Override
    @Transactional(readOnly = true)
    //consultar un entregable en la BD dado un id existente
    public Optional<Entregable> consultarPorIdEntregable(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entregable> consultarTodosEntregables() {
        return repositorio.findAll();
    }

    @Override
    @Transactional
    //actualizar entregable  dado un id existente
    public Entregable modificarEntregable(Entregable entregable) throws NoSuchFieldException {
        if (repositorio.findById(entregable.getId()).isPresent()){
            return repositorio.save(entregable);
        }
        else{
            throw new NoSuchFieldException("No existe el entregable con id: " + entregable.getId());
        }
    }

    @Override
    @Transactional
    //eliminar un entregable existente
    public void eliminarEnregable(Integer id) {
        repositorio.deleteById(id);
    }
}
